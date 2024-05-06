package com.tellingus.tellingme.data.repositoryimpl

import android.util.Log
import com.tellingus.tellingme.data.model.home.NoticeResponse
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
    override suspend fun getQuestion(): ApiResult<NoticeResponse> {
        return networkService.getNotice()


    }
}