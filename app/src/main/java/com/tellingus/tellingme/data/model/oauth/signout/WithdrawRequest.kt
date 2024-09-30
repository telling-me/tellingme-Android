package com.tellingus.tellingme.data.model.oauth.signout

data class WithdrawRequest(
    val authorizationCode: String = "app"
)