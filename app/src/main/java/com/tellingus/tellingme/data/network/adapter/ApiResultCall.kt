package com.tellingus.tellingme.data.network.adapter

import android.util.Log
import com.google.gson.Gson
import com.tellingus.tellingme.util.TAG
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResultCall<T>(private val delegate: Call<T>) : Call<ApiResult<T>> {
    private val gson = Gson()
    override fun enqueue(callback: Callback<ApiResult<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful || response.code() == 404) {
                        Log.d(TAG,"1")
                        callback.onResponse(
                            this@ApiResultCall,
                            Response.success(
                                ApiResult.Success(response.body()!!, response.code())
                            )
                        )
                    } else {
                        Log.d(TAG,"2")
                        val message = if (response.errorBody() == null) ErrorResponse(
                            message = "알 수 없는 오류가 발생했습니다.",
                            code = response.code(),
                        ) else gson.fromJson(
                            response.errorBody()!!.string(),
                            ErrorResponse::class.java
                        )
                        Log.d(TAG, message.toString())

                        callback.onResponse(
                            this@ApiResultCall,
                            Response.success(
                                ApiResult.Failure(message.message, message.code)
                            )
                        )
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