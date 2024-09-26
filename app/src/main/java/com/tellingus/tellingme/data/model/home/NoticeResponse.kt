package com.tellingus.tellingme.data.model.home

data class NoticeResponse(
    val code: Int,
    val message: String,
    val data: List<NoticeResponse>
)

data class Notice(
    val noticeId: Int,
    val title:String,
    val content: String,
    val isRead: Boolean,
    val createdAt: String,
    val link: String,
    val isInternal: Boolean,
    val answerId: Int,
    val date: String
)
