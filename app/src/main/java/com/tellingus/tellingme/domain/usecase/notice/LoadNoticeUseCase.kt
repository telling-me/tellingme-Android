package com.tellingus.tellingme.domain.usecase.notice

import com.tellingus.tellingme.data.model.notice.LoadNoticeResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.NoticeRepository
import javax.inject.Inject

class LoadNoticeUseCase @Inject constructor(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(): ApiResult<List<LoadNoticeResponse>> {
        return noticeRepository.loadNotice()
    }
}