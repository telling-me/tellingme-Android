package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import com.tellingus.tellingme.data.model.oauth.login.JoinRequestDto
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class SignupContract {
    data class State(
        val joinRequestDto: JoinRequestDto = JoinRequestDto(),
        val nicknameErrorState: String = ""
    ): UiState

    sealed class Event: UiEvent {
        object NextButtonClickedInNickname: Event()
        object NextButtonClickedInBirthGender: Event()
        object NextButtonClickedInJob: Event()
        object NextButtonClickedInWorry: Event()

    }

    sealed class Effect: UiEffect {
//        object MoveToNickname : Effect()    시작이 닉네임
        object MoveToBirthGender : Effect()
        object MoveToJob : Effect()
        object MoveToWorry : Effect()
    }
}