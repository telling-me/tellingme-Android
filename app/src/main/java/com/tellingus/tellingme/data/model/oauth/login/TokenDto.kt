package com.tellingus.tellingme.data.model.oauth.login

data class TokenDto(
    val accessToken: String = "",
    val refreshToken: String = "",
    val socialId: String = "",
)
