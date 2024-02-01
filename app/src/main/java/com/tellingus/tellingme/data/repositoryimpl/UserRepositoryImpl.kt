package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.login.OauthRequestDto
import com.tellingus.tellingme.data.model.login.TokenDto
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val service: NetworkService
): UserRepository {
    override suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenDto> {
        return service.loginFromKakao(oauthToken, loginType, isAuto, oauthRequestDto)
    }

}