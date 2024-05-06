package com.tellingus.tellingme.data.model.oauth.login

data class JwtTokenBody(
    val accessToken: String,
    val refreshToken: String
)