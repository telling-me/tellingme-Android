package com.tellingus.tellingme.data.network.token

import android.util.Log
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.data.repositoryimpl.DataStoreRepositoryImpl
import com.tellingus.tellingme.di.AuthNetworkService
import com.tellingus.tellingme.domain.repository.AuthRepository
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.HttpException
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    @AuthNetworkService private val networkService: NetworkService
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = runBlocking { dataStoreRepository.getAccessToken().firstOrNull() }
        val refreshToken = runBlocking { dataStoreRepository.getRefreshToken().firstOrNull() }

        if (accessToken == null || refreshToken == null) {
            return null
        }

        return runBlocking {
            val apiResult = networkService.refreshAccessToken(accessToken = accessToken, refreshToken = refreshToken)

            apiResult.onSuccess {
                dataStoreRepository.setJwtTokens(accessToken = it.accessToken, refreshToken = it.refreshToken)
            }.onFailure { m, c ->
                Log.d("taag tokenAuthenticator f", m)
                Log.d("taag tokenAuthenticator f", c.toString())
                return@runBlocking null
            }
            val newAccessToken = dataStoreRepository.getAccessToken().firstOrNull()

            response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        }
    }
}