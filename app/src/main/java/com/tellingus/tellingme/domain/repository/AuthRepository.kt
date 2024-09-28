package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenResponse
import com.tellingus.tellingme.data.model.oauth.signup.JoinRequestDto
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequest
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
    ): ApiResult<BasicResponse>

    suspend fun joinUser(
        joinRequestDto: JoinRequestDto
    ): ApiResult<BasicResponse>

    suspend fun refreshAccessToken(
        accessToken: String,
        refreshToken: String
    ): ApiResult<TokenResponse>

    suspend fun signOutUser(): ApiResult<BasicResponse>
}