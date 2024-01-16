package com.tellingus.tellingme.domain.repository

import android.content.Context
import com.tellingus.tellingme.data.model.DefaultResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun kakaoLogin(): Response<DefaultResponse>
    suspend fun isKakaoTalkLoginAvailable(context: Context) // 카카오톡 설치 유무 확인
    suspend fun loginWithKakaoTalk(context: Context) // 카카오 로그인 with app
    suspend fun loginWithKakaoAccount(context: Context) // 카카오 로그인 with web
}