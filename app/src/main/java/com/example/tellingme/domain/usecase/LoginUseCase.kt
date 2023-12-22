package com.example.tellingme.domain.usecase

import com.example.tellingme.data.model.DefaultResponse
import com.example.tellingme.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke() : Response<DefaultResponse> {
        return loginRepository.kakaoLogin()
    }
}