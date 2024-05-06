package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.notice.LoadNoticeResponse
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.NoticeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoticeRepositoryImpl @Inject constructor(
    private val service: NetworkService
) : NoticeRepository{
    override suspend fun loadNotice(): ApiResult<List<LoadNoticeResponse>> {
        return service.loadNotice()
    }
}