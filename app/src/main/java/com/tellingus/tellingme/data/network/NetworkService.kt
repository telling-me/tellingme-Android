package com.tellingus.tellingme.data.network

import com.tellingus.tellingme.data.model.oauth.dto.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.response.TokenDto
import com.tellingus.tellingme.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkService {

    // 소셜로그인 API
    @POST("/api/oauth/{loginType}/{isAuto}")
    suspend fun loginFromKakao(
        @Header("oauthToken") oauthToken: String,
        @Path("loginType") loginType: String,
        @Path("isAuto") isAuto: String,
        @Body oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenDto>


}