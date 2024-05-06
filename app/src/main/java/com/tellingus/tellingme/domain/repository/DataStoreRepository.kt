package com.tellingus.tellingme.domain.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreRepository {
    suspend fun setUserSocialId(socialId: String)
    suspend fun getUserSocialId(): Flow<String>

    suspend fun setJwtTokens(
        accessToken: String,
        refreshToken: String
    )

    suspend fun getAccessToken(): Flow<String>
    suspend fun getRefreshToken(): Flow<String>

    suspend fun deleteTokens()

}