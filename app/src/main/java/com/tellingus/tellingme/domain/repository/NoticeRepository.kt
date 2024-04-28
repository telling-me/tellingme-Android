package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.notice.NoticeResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult

interface NoticeRepository {

    suspend fun loadNotice(): ApiResult<List<NoticeResponse>>
}