package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenResponse
import com.tellingus.tellingme.data.model.oauth.signup.SignupRequest
import com.tellingus.tellingme.data.model.oauth.signup.NicknameResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult

interface AuthRepository {
    suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenResponse>

    suspend fun verifyNickname(
        nickname: String
    ): ApiResult<NicknameResponse>

    suspend fun signupUser(
        signupRequest: SignupRequest
    ): ApiResult<BasicResponse>

    suspend fun refreshAccessToken(
        accessToken: String,
        refreshToken: String
    ): ApiResult<TokenResponse>

    suspend fun signOutUser(): ApiResult<BasicResponse>
}