package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import com.tellingus.tellingme.data.model.oauth.signup.JoinRequestDto
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class SignupContract {
    data class State(
        val isLoading: Boolean = false,
        val joinRequestDto: JoinRequestDto = JoinRequestDto(),
        val nicknameErrorState: String? = null
    ): UiState

    sealed class Event: UiEvent {
        object NextButtonClickedInNickname: Event()

        data class NextButtonClickedInBirthGender(
            val birth: String,
            val gender: String
        ): Event()

        data class NextButtonClickedInJob(
            val job: Int
        ): Event()

        object NextButtonClickedInWorry: Event()

    }

    sealed class Effect: UiEffect {
//        object MoveToNickname : Effect()    시작이 닉네임
        object MoveToBirthGender : Effect()
        object MoveToJob : Effect()
        object MoveToWorry : Effect()
    }
}