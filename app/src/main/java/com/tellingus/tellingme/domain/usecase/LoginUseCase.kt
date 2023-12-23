package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.DefaultResponse
import com.tellingus.tellingme.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke() : Response<DefaultResponse> {
        return loginRepository.kakaoLogin()
    }
}