package com.tellingus.tellingme.data.model.oauth.response

data class TokenDto(
    val accessToken: String = "",
    val refreshToken: String = "",
    val socialId: String = "",
)
