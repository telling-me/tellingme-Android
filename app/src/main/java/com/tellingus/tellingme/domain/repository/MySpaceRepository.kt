package com.tellingus.tellingme.domain.repository

import com.tellingus.tellingme.data.model.myspace.AnswerListResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult

interface MySpaceRepository {
    suspend fun getAnswerList(
        year: String,
        month: String
    ): ApiResult<AnswerListResponse>

}