package com.example.tellingme.domain.repository

import com.example.tellingme.data.model.DefaultResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun kakaoLogin(): Response<DefaultResponse>
}