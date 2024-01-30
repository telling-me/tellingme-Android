package com.tellingus.tellingme.data.network.adapter

data class ErrorResponse(
    val isSuccess: Boolean = false,
    val code: Int = 0,
    val message: String = "",
)