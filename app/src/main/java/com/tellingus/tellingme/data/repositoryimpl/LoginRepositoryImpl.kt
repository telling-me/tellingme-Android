package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.DefaultResponse
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val service: NetworkService
): LoginRepository {
    override suspend fun kakaoLogin(): Response<DefaultResponse> {
        return service.postKakaoLogin()
    }

}