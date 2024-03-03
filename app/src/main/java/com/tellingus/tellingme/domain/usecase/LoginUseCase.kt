package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenDto
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ) : ApiResult<TokenDto> {
        return loginRepository.loginFromKakao(oauthToken, loginType, isAuto, oauthRequestDto)
    }
}