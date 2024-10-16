package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.home.QuestionResponse
import com.tellingus.tellingme.data.model.myspace.AnswerListResponse
import com.tellingus.tellingme.data.model.oauth.signup.NicknameResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.HomeRepository
import com.tellingus.tellingme.domain.repository.MySpaceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnswerListUseCase @Inject constructor(
    private val mySpaceRepository: MySpaceRepository
) {
    suspend operator fun invoke(
        year: String,
        month: String
    ) : ApiResult<AnswerListResponse> {
        return mySpaceRepository.getAnswerList(year, month)
    }
}