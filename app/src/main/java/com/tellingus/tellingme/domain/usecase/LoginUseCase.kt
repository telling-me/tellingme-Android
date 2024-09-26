package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ) : ApiResult<TokenResponse> {
        return authRepository.loginFromKakao(oauthToken, loginType, isAuto, oauthRequestDto)
    }
}