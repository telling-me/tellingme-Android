package com.example.tellingme.data.repositoryimpl

import com.example.tellingme.data.model.DefaultResponse
import com.example.tellingme.data.network.NetworkService
import com.example.tellingme.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl(
    private val service: NetworkService
): LoginRepository {
    override suspend fun kakaoLogin(): Response<DefaultResponse> {
        return service.postKakaoLogin()
    }

}