package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequest
import com.tellingus.tellingme.data.model.oauth.login.TokenResponse
import com.tellingus.tellingme.data.model.oauth.signup.SignUpRequest
import com.tellingus.tellingme.data.model.oauth.signup.NicknameResponse
import com.tellingus.tellingme.data.model.oauth.signup.SignUpResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult

interface AuthRepository {
    suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequest: OauthRequest
    ): ApiResult<TokenResponse>

    suspend fun verifyNickname(
        nickname: String
    ): ApiResult<NicknameResponse>

    suspend fun signUpUser(
        signupRequest: SignUpRequest
    ): ApiResult<SignUpResponse>

    suspend fun refreshAccessToken(
        accessToken: String,
        refreshToken: String
    ): ApiResult<TokenResponse>

    suspend fun signOutUser(): ApiResult<BasicResponse>
}