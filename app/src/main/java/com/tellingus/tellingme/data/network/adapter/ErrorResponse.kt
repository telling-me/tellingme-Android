package com.tellingus.tellingme.data.network.adapter

data class ErrorResponse(
    val status: Int = 0,
    val code: String = "",
    val message: String = "",
)