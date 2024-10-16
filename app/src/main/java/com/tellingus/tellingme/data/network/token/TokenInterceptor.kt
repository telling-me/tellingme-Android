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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    @AuthNetworkService private val networkService: NetworkService,
    private val dataStoreRepository: DataStoreRepository,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { dataStoreRepository.getAccessToken().firstOrNull() }
        val refreshToken = runBlocking { dataStoreRepository.getRefreshToken().firstOrNull() }
        var request = chain.request()

        if (accessToken != null) {
            request = request.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
        }

        // 첫 번째 응답을 진행
        val response = chain.proceed(request)

        // 만약 응답 코드가 401 또는 403이면 토큰 갱신을 시도
        if (response.code == 403) {
            Log.d("taag", "403임")
//            // 토큰이 없으면 바로 반환
            if (accessToken == null || refreshToken == null) {
                return response
            }

//            // 새로운 access token 발급 시도
            val newRequest = runBlocking {
                val apiResult = networkService.refreshAccessToken(
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )

                apiResult.onSuccess {
                    request = request.newBuilder()
                        .header("Authorization", "Bearer ${it.data.refreshToken}")
                        .build()
                    dataStoreRepository.setJwtTokens(accessToken = it.data.accessToken, refreshToken = it.data.refreshToken)
                }.onFailure { m, c ->
                    Log.d("taag tokenInterceptor failure", m)
                    Log.d("taag tokenInterceptor failure", c.toString())
                    return@runBlocking null
                }
            }

            // 새로운 토큰으로 재요청
            return newRequest?.let { chain.proceed(request) } ?: response
        }

        // 토큰 갱신이 필요하지 않으면 기존 응답 반환
        return response

        // 원래코드
//        val accessToken = runBlocking { dataStoreRepository.getAccessToken().first() }
//
//        val request = chain.request().newBuilder().apply {
//            header("Authorization", accessToken)
//        }
//        return chain.proceed(request.build())
    }
}
