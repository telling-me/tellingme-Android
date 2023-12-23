package com.tellingus.tellingme.data.network

import com.tellingus.tellingme.data.model.DefaultResponse
import retrofit2.Response
import retrofit2.http.POST

interface NetworkService {

    @POST
    suspend fun postKakaoLogin(

    ): Response<DefaultResponse>

}