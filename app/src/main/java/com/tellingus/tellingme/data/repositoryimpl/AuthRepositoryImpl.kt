package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequest
import com.tellingus.tellingme.data.model.oauth.login.TokenResponse
import com.tellingus.tellingme.data.model.oauth.signout.SignOutRequest
import com.tellingus.tellingme.data.model.oauth.signup.SignUpRequest
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequest
import com.tellingus.tellingme.data.model.oauth.signup.NicknameResponse
import com.tellingus.tellingme.data.model.oauth.signup.SignUpResponse
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
        oauthRequest: OauthRequest
    ): ApiResult<TokenResponse> {
        return service.loginFromKakao(oauthToken, loginType, isAuto, oauthRequest)
    }

    override suspend fun verifyNickname(nickname: String): ApiResult<NicknameResponse> {
        return service.verifyNickname(NicknameRequest(nickname = nickname))
    }

    override suspend fun signUpUser(signupRequest: SignUpRequest): ApiResult<SignUpResponse> {
        return service.signUpUser(signupRequest)
    }

    override suspend fun refreshAccessToken(
        accessToken: String,
        refreshToken: String
    ): ApiResult<TokenResponse> {
        return service.refreshAccessToken(accessToken, refreshToken)
    }

    override suspend fun signOutUser(): ApiResult<BasicResponse> {
        return service.signOutUser(signoutRequest = SignOutRequest())
    }

}