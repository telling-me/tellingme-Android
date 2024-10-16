package com.tellingus.tellingme.domain.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreRepository {
    suspend fun setString(key: String, value: String)
    suspend fun setInt(key: String, value: Int)
    suspend fun setBoolean(key: String, value: Boolean)
    suspend fun getString(key: String): Flow<String>
    suspend fun getInt(key: String): Flow<Int>
    suspend fun getBoolean(key: String): Flow<Boolean>

    // 아래 제거 필요
    suspend fun setJwtTokens(
        accessToken: String,
        refreshToken: String
    )

    suspend fun getAccessToken(): Flow<String>
    suspend fun getRefreshToken(): Flow<String>

    suspend fun deleteTokens()
}

object DataStoreKey {
    const val SOCIAL_ID = "socialId"

}