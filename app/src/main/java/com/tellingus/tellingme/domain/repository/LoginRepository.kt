package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.login.LoginRequestBody
import com.tellingus.tellingme.data.model.login.LoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        loginRequestBody: LoginRequestBody
    ): Response<LoginResponse>
}