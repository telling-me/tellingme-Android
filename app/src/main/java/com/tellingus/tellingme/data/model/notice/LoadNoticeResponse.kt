package com.tellingus.tellingme.data.model.notice

// TODO: 홈 NoticeResponse 테스트 완료 후 NoticeResponse 로 이름 수정
data class LoadNoticeResponse(
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