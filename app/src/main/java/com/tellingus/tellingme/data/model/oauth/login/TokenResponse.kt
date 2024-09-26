package com.tellingus.tellingme.data.model.oauth.login

data class TokenResponse(
    val code: Int,
    val message: String,
    val data: Token
)

data class Token(
    val accessToken: String = "",
    val refreshToken: String = "",
    val socialId: String = "",
)