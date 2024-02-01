package com.tellingus.tellingme.data.model.login

data class TokenDto(
    val accessToken: String = "",
    val refreshToken: String = "",
    val socialId: String = "",
)
