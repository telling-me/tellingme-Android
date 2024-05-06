//package com.tellingus.tellingme.data.network.token
//
//import android.util.Log
//import com.numberone.daepiro.repository.TokenRepository
//import com.tellingus.tellingme.data.repositoryimpl.DataStoreRepositoryImpl
//import com.tellingus.tellingme.domain.repository.DataStoreRepository
//import kotlinx.coroutines.flow.firstOrNull
//import kotlinx.coroutines.runBlocking
//import okhttp3.Authenticator
//import okhttp3.Request
//import okhttp3.Response
//import okhttp3.Route
//import retrofit2.HttpException
//import javax.inject.Inject
//
//class TokenAuthenticator @Inject constructor(
//    private val dataStoreRepository: DataStoreRepository
//): Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        Log.d("taag code", response.code.toString())
//        val accessToken = runBlocking { dataStoreRepository.getAccessToken().firstOrNull() }
//        val refreshToken = runBlocking { dataStoreRepository.getRefreshToken().firstOrNull() }
//
//        if (accessToken == null || refreshToken == null) {
//            return null
//        }
//
//        return runBlocking {
//            try {
//                dataStoreRepository.refreshAccessToken(accessToken = accessToken, refreshToken = refreshToken).firstOrNull()
//            } catch (e: HttpException) {
//                Log.d("taag", "123")
//                return@runBlocking null
//            }
//
//            response.request.newBuilder()
//                .header("authorization", "Bearer ${tokenRepository.getAccessToken()}")
//                .build()
//
//            val newAccessToken = tokenRepository.refreshAccessToken(token = refreshToken).firstOrNull()
//
//            Log.d("taag newAccess", newAccessToken?.accessToken.toString())
//
//            if (newAccessToken == null) {
//                tokenRepository.deleteTokens()
//                null
//            } else {
//                response.request.newBuilder()
//                    .header("authorization", "Bearer $newAccessToken")
//                    .build()
//            }
//        }
//    }
//}