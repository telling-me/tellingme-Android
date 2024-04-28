package com.tellingus.tellingme.data.model.notice

data class NoticeResponse(
    val noticeId: Int,
    val title:String,
    val content: String,
    val isRead: Boolean,
    val createdAt: String,
    val link: String,
    val isInternal: Boolean,
    val answerId: Int,
    val date: String,
)