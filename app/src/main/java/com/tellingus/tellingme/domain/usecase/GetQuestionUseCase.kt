package com.tellingus.tellingme.domain.usecase

import com.tellingus.tellingme.data.model.home.QuestionResponse
import com.tellingus.tellingme.data.model.oauth.signup.NicknameResponse
import com.tellingus.tellingme.data.network.adapter.ApiResult
import com.tellingus.tellingme.domain.repository.HomeRepository

class GetQuestionUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(
        today: String
    ) : ApiResult<QuestionResponse> {
        return homeRepository.getQuestion(today)
    }
}