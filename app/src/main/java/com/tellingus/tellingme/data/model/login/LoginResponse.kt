package com.tellingus.tellingme.data.model.login

data class LoginResponse(
    val accessToken: String = "",
    val refreshToken: String = "",
    val socialId: String = "",
    val socialLoginType: String = ""
)

data class LoginFailResponse(
    val socialId: String,
    val socialLoginType: String
)