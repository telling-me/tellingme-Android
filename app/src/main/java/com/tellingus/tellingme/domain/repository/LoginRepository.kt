package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.oauth.dto.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.response.TokenDto
import com.tellingus.tellingme.data.network.adapter.ApiResult

interface LoginRepository {
    suspend fun loginFromKakao(
        oauthToken: String,
        loginType: String,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenDto>

}