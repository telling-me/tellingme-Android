package com.tellingus.tellingme.data.model.common

data class ErrorResponse(
    val status: Int = 0,
    val code: String = "",
    val message: String = "",
)