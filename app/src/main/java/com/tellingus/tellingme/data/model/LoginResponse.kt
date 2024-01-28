package com.tellingus.tellingme.data.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val socialId: String
)
