package com.tellingus.tellingme.data.network.adapter

import android.util.Log
import com.google.gson.Gson
import com.tellingus.tellingme.data.model.login.LoginFailResponse
import com.tellingus.tellingme.data.model.login.LoginResponse
import com.tellingus.tellingme.util.TAG
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiResultCall<T>(private val delegate: Call<T>) : Call<ApiResult<T>> {
    private val gson = Gson()

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    // response.isSuccessful은 status가 200..299인 경우 작동
                    Log.d(TAG+"status", response.code().toString())
                    if (response.isSuccessful) {
                        Log.d(TAG,"1")
                        callback.onResponse(
                            this@ApiResultCall,
                            Response.success(
                                ApiResult.Success(response.body()!!, response.code())
                            )
                        )
                    } else {
                        Log.d(TAG,"2")
                        if (response.code() == 404) {
                            if (response.errorBody() != null) {
                                Log.d(TAG, response.errorBody()?.string()!!)
                                val errorBody = gson.fromJson(
                                    response.errorBody()?.string()!!,
                                    LoginFailResponse::class.java
                                )
                                Log.d("taag 1", errorBody?.toString()!!)
                                callback.onResponse(
                                    this@ApiResultCall,
                                    Response.success(
                                        ApiResult.Failure(errorBody.socialId, response.code())
                                    )
                                )
                            }
                        } else {
                            val message = if (response.errorBody() == null) {
                                ErrorResponse(
                                    status = response.code(),
                                    message = "알 수 없는 오류가 발생했습니다.",
                                )
                            } else gson.fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)

                            callback.onResponse(
                                this@ApiResultCall,
                                Response.success(
                                    ApiResult.Failure(message.message, message.status)
                                )
                            )
                        }
                    }
                }

                // 서버에서 받는 에러는 모두 Response.success로 감싸져 옴
                // 따라서, onFailure가 호출되는 경우는 인터넷 연결 오류밖에 없음
                override fun onFailure(call: Call<T>, t: Throwable) {
                    callback.onResponse(
                        this@ApiResultCall,
                        Response.success(
                            ApiResult.NetworkError(t)
                        )
                    )
                }
            }
        )
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun execute(): Response<ApiResult<T>> {
        throw UnsupportedOperationException("ResponseCall does not support execute.")
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun clone(): Call<ApiResult<T>> {
        return ApiResultCall(delegate.clone())
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }
}