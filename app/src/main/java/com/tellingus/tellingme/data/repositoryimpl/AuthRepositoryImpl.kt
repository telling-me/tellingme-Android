package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenDto
import com.tellingus.tellingme.data.model.oauth.signup.JoinRequestDto
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequestDto
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val service: NetworkService
): AuthRepository {
    override suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenDto> {
        return service.loginFromKakao(oauthToken, loginType, isAuto, oauthRequestDto)
    }

    override suspend fun verifyNickname(nicknameRequestDto: NicknameRequestDto): ApiResult<BasicResponse> {
        return service.verifyNickname(nicknameRequestDto)
    }

    override suspend fun joinUser(joinRequestDto: JoinRequestDto): ApiResult<BasicResponse> {
        return service.joinUser(joinRequestDto)
    }

    override suspend fun refreshAccessToken(
        accessToken: String,
        refreshToken: String
    ): ApiResult<TokenDto> {
        return service.refreshAccessToken(accessToken, refreshToken)
    }

}