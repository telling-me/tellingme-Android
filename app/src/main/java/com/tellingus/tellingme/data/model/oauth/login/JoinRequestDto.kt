package com.tellingus.tellingme.data.model.login

data class JoinRequestDto(
    val socialId: String = "",
    val socialLoginType: String = "",
    val nickname: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val purpose: String = "",
    val job: Int = 0,
    val jobInfo: String = "",
)
