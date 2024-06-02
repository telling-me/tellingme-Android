package com.tellingus.tellingme.data.network

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.home.NoticeResponse
import com.tellingus.tellingme.data.model.notice.LoadNoticeResponse
import com.tellingus.tellingme.data.model.oauth.login.JwtTokenBody
import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.model.oauth.login.TokenDto
import com.tellingus.tellingme.data.model.oauth.signup.JoinRequestDto
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequestDto
import com.tellingus.tellingme.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


const val END_POINT: String = "/api"

interface NetworkService {

    // 소셜로그인 API
    @POST("${END_POINT}/oauth/{loginType}/{isAuto}")
    suspend fun loginFromKakao(
        @Header("oauthToken") oauthToken: String,
        @Path("loginType") loginType: String,
        @Path("isAuto") isAuto: String,
        @Body oauthRequestDto: OauthRequestDto
    ): ApiResult<TokenDto>

    // 닉네임 유효성 검사 API
    @POST("${END_POINT}/oauth/nickname")
    suspend fun verifyNickname(
        @Body nicknameRequestDto: NicknameRequestDto
    ): ApiResult<BasicResponse>

    // 추가 정보 기입 API
    @POST("${END_POINT}/oauth/join")
    suspend fun joinUser(
        @Body joinRequestDto: JoinRequestDto
    ): ApiResult<BasicResponse>

    @GET("${END_POINT}/notice")
    suspend fun loadNotice(): ApiResult<List<LoadNoticeResponse>>

    // 알림 조회 API
    @GET("${END_POINT}/notice")
    suspend fun getNotice(): ApiResult<NoticeResponse>

    // 토큰 갱신 API
    @POST("${END_POINT}/token")
    suspend fun refreshAccessToken(
        @Header("accessToken") accessToken: String,
        @Header("refreshToken") refreshToken: String
    ): ApiResult<TokenDto>


}
