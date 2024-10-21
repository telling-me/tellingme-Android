package com.tellingus.tellingme.data.network

import com.tellingus.tellingme.data.model.common.BasicResponse
import com.tellingus.tellingme.data.model.home.NoticeResponse
import com.tellingus.tellingme.data.model.home.QuestionRequest
import com.tellingus.tellingme.data.model.home.QuestionResponse
import com.tellingus.tellingme.data.model.myspace.AnswerListResponse
import com.tellingus.tellingme.data.model.notice.LoadNoticeResponse
import com.tellingus.tellingme.data.model.oauth.login.OauthRequest
import com.tellingus.tellingme.data.model.oauth.login.TokenResponse
import com.tellingus.tellingme.data.model.oauth.signout.SignOutRequest
import com.tellingus.tellingme.data.model.oauth.signup.SignUpRequest
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequest
import com.tellingus.tellingme.data.model.oauth.signup.NicknameResponse
import com.tellingus.tellingme.data.model.oauth.signup.SignUpResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


const val END_POINT: String = "/api"

interface NetworkService {

    // 소셜로그인 API
    @POST("${END_POINT}/oauth/{loginType}/{isAuto}")
    suspend fun loginFromKakao(
        @Header("oauthToken") oauthToken: String,
        @Path("loginType") loginType: String,
        @Path("isAuto") isAuto: String,
        @Body oauthRequest: OauthRequest
    ): ApiResult<TokenResponse>

    // 닉네임 유효성 검사 API
    @POST("${END_POINT}/oauth/nickname")
    suspend fun verifyNickname(
        @Body nicknameRequest: NicknameRequest
    ): ApiResult<NicknameResponse>

    // 추가 정보 기입 API
    @POST("${END_POINT}/oauth/join")
    suspend fun signUpUser(
        @Body signupRequest: SignUpRequest
    ): ApiResult<SignUpResponse>

    @GET("${END_POINT}/notice")
    suspend fun loadNotice(): ApiResult<LoadNoticeResponse>

    // 오늘의 질문 조회 API
    @POST("${END_POINT}/question")
    suspend fun getQuestion(
        @Body questionRequest: QuestionRequest
    ): ApiResult<QuestionResponse>

    // 알림 조회 API
    @GET("${END_POINT}/notice")
    suspend fun getNotice(): ApiResult<NoticeResponse>

    // 토큰 갱신 API
    @POST("${END_POINT}/token")
    suspend fun refreshAccessToken(
        @Header("accessToken") accessToken: String,
        @Header("refreshToken") refreshToken: String
    ): ApiResult<TokenResponse>

    // 회원 탈퇴 API
    @POST("${END_POINT}/oauth/withdraw/rn")
    suspend fun signOutUser(
        @Body signoutRequest: SignOutRequest
    ): ApiResult<BasicResponse>

    // 내 답변 리스트 조회 API
    @GET("${END_POINT}/answer/list")
    suspend fun getAnswerList(
        @Query("year") year: String,
        @Query("month") month: String
    ): ApiResult<AnswerListResponse>

}
