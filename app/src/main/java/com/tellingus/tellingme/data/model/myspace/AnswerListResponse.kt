package com.tellingus.tellingme.data.model.myspace

data class AnswerListResponse(
    val code: Int,
    val message: String,
    val data: List<Answer>
)

data class Answer(
    val emotion: Int = 0,
    val title: String = "",
    val phrase: String = "",
    val date: String = "",
    val content: String = "",
    val spareTitle: String = "",
    val sparePhrase: String = "",
    val isSpare: Boolean = false,
)