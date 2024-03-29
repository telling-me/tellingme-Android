package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenDto
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequestDto
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyNicknameUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        nicknameRequestDto: NicknameRequestDto
    ) : ApiResult<BasicResponse> {
        return authRepository.verifyNickname(nicknameRequestDto)
    }
}