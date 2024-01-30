package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.login.LoginRequestBody
import com.tellingus.tellingme.data.model.login.LoginResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        loginRequestBody: LoginRequestBody
    ) : ApiResult<LoginResponse> {
        return loginRepository.loginFromKakao(oauthToken, loginType, isAuto, loginRequestBody)
    }
}