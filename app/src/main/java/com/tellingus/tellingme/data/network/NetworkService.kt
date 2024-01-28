package com.tellingus.tellingme.data.network

import com.tellingus.tellingme.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface NetworkService {

    @POST("")
    suspend fun loginFromKakao(

    ): Response<LoginResponse>

}