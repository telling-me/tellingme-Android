package com.tellingus.tellingme.data.model.common

data class BasicResponse(
    val code: Int = 0,
    val message: String = "",
    val data: Boolean = false,
)
