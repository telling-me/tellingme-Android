package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.oauth.signup.SignUpRequest
import com.tellingus.tellingme.data.model.oauth.signup.SignUpResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        signupRequest: SignUpRequest
    ) : ApiResult<SignUpResponse> {
        return authRepository.signUpUser(signupRequest)
    }
}