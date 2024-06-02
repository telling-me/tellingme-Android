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
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            dataStoreRepository.getAccessToken().first()
        }

        val request = chain.request().newBuilder().apply {
            header("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(request.build())
    }
}