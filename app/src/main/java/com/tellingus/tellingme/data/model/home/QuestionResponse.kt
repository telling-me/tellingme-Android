package com.tellingus.tellingme.data.model.home

data class QuestionResponse(
    val date: String,
    val title: String,
    val phrase: String,
    val spareTitle: String,
    val sparePhrase: String,
    val userQuestionType: String
)