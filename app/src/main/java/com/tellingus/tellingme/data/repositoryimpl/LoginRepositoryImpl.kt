package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.oauth.dto.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.response.TokenDto
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val service: NetworkService
): LoginRepository {
    override suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenDto> {
        return service.loginFromKakao(oauthToken, loginType, isAuto, oauthRequestDto)
    }

}