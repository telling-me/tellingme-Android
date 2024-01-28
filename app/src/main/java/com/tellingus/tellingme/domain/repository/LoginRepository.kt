package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.LoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun loginFromKakao(): Response<LoginResponse>
}