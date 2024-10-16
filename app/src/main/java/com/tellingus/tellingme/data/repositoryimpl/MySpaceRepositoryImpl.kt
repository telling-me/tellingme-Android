package com.tellingus.tellingme.data.repositoryimpl

import com.tellingus.tellingme.data.model.home.NoticeResponse
import com.tellingus.tellingme.data.model.home.QuestionRequest
import com.tellingus.tellingme.data.model.home.QuestionResponse
import com.tellingus.tellingme.data.model.myspace.AnswerListResponse
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.HomeRepository
import com.tellingus.tellingme.domain.repository.MySpaceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySpaceRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
): MySpaceRepository {
    override suspend fun getAnswerList(
        year: String,
        month: String
    ): ApiResult<AnswerListResponse> {
        return networkService.getAnswerList(year, month)
    }
}