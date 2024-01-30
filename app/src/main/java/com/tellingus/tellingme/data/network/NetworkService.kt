package com.tellingus.tellingme.data.network

import com.tellingus.tellingme.data.model.login.LoginRequestBody
import com.tellingus.tellingme.data.model.login.LoginResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import retrofit2.Response
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
        @Body loginRequestBody: LoginRequestBody
    ): ApiResult<LoginResponse>


}