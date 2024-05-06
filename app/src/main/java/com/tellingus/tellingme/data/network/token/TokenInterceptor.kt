package com.tellingus.tellingme.data.network.token

import android.util.Log
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.di.AuthNetworkService
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    @AuthNetworkService private val networkService: NetworkService
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            dataStoreRepository.getAccessToken().first()
        }

        Log.d("taag Interceptor accessToken", accessToken)

        val request = chain.request().newBuilder().apply {
            header("authorization", "Bearer $accessToken")
        }
        return chain.proceed(request.build())

//        val accessToken = runBlocking {
//            dataStoreRepository.getAccessToken().first()
//        }
//
//        val originRequest: Request = chain.request()
//        val request = if (accessToken.isNotEmpty()) {
//            originRequest.newBuilder()
//                .header("authorization", "Bearer $accessToken")
//                .build()
//        } else {
//            originRequest
//        }
//
//        var response = chain.proceed(request)
//
//        runBlocking {
//            Log.d("taag interceptor status", response.code.toString())
//            Log.d("taag interceptor prev access", dataStoreRepository.getAccessToken().first())
//            Log.d("taag interceptor prev refresh", dataStoreRepository.getRefreshToken().first())
//        }
//
//        if (response.code == 2000) {
//            Log.d("taag", "2000 AccessToken 만료")
//            response.close()
//            runBlocking {
//                val refreshToken = dataStoreRepository.getRefreshToken().first()
//                val newJwtTokens = networkService.refreshAccessToken(accessToken, refreshToken)
//
//                newJwtTokens.onSuccess {
//                    Log.d("taag newJwtTones", it.toString())
//                }.onFailure { s, i ->
//                    Log.d("taag newJwtTones", {"$s - $i"}.toString())
//                }
//
////                newJwtTokens.catch { e ->
////                    when (e) {
////                        is HttpException -> {
////                            Log.d("taag newjwt erro", "refreshToken까지 만료?")
////                        }
////                        else -> {
////
////                        }
////                    }
////                }.collectLatest {
////                    Log.d("taag pre acc", dataStoreRepository.getAccessToken().first())
////                    dataStoreRepository.setJwtTokens(accessToken = it.accessToken, refreshToken = it.refreshToken)
////                    Log.d("taag new acc", dataStoreRepository.getAccessToken().first())
////                    Log.d("taag new acc", "accessToken 만료돼서 갱신함")
////
////
////                    val newRequest = response.request.newBuilder()
////                        .header("authorization", "Bearer ${dataStoreRepository.getAccessToken().first()}")
////                        .build()
////
////                    response = chain.proceed(newRequest)
////                }
//            }
//        }
//
//        return response
////        return chain.proceed(request)
    }
}