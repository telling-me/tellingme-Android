package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.login.OauthRequestDto
import com.tellingus.tellingme.data.model.login.TokenDto
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ) : ApiResult<TokenDto> {
        return userRepository.loginFromKakao(oauthToken, loginType, isAuto, oauthRequestDto)
    }
}