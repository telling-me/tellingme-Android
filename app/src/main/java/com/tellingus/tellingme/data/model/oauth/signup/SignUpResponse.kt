package com.tellingus.tellingme.data.model.oauth.signup

data class SignUpResponse(
    val code: Int,
    val message: String,
    val data: SignUp
)

data class SignUp(
    val result: Boolean = false
)