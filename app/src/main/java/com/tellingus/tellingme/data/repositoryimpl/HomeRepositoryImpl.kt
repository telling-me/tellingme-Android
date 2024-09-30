package com.tellingus.tellingme.data.repositoryimpl

import android.util.Log
import com.tellingus.tellingme.data.model.home.NoticeResponse
import com.tellingus.tellingme.data.model.home.QuestionRequest
import com.tellingus.tellingme.data.model.home.QuestionResponse
import com.tellingus.tellingme.data.model.home.TodayQuestion
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.di.AuthNetworkService
import com.tellingus.tellingme.domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
): HomeRepository {
    override suspend fun getNotice(): ApiResult<NoticeResponse> {
        return networkService.getNotice()


    }

    override suspend fun getQuestion(today: String): ApiResult<QuestionResponse> {
        return networkService.getQuestion(QuestionRequest(date = today))


    }
}