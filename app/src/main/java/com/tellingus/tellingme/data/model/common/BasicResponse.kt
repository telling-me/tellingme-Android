package com.tellingus.tellingme.data.model.common

data class BasicResponse(
    val status: Int = 0,
    val code: String = "",
    val message: String = "",
)

//data class BasicResponse2(
//    val data: T? = null,
//    val code: String = "",
//    val message: String = "",
//)