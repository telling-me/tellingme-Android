package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.home.TodayQuestion
import com.tellingus.tellingme.data.network.adapter.ApiResult

interface HomeRepository {
    suspend fun loadTodayQuestion() : ApiResult<TodayQuestion>
}