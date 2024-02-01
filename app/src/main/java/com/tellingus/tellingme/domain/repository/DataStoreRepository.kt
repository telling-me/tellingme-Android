package com.tellingus.tellingme.domain.repository

import kotlinx.coroutines.flow.Flow


interface DataStoreRepository {
    suspend fun setUserSocialId(socialId: String)
    suspend fun getUserSocialId(): Flow<String>

}