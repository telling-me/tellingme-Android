package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class SignupContract {
    data class State(
        val socialId: String = "",
        val socialLoginType: String = "",
        val nickname: String = "",
        val gender: String = "",
        val birthDate: String = "",
        val purpose: String = "",
        val job: Int = 0,
        val jobInfo: String = "",
    ): UiState

    sealed class Event: UiEvent {

    }

    sealed class Effect: UiEffect {
        data class MoveToSignup(
            val socialId: String
        ): Effect()
        object MoveToHome : Effect()
    }
}