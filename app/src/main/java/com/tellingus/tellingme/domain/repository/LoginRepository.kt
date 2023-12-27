package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.DefaultResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun kakaoLogin(): Response<DefaultResponse>
}