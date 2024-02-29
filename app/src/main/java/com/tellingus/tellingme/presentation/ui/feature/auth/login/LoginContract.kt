package com.tellingus.tellingme.presentation.ui.feature.auth.login

import android.content.Context
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class LoginContract {
    data class State(
        val isLoading: Boolean = false
    ): UiState

    sealed class Event: UiEvent {
        data class KakaoLoginButtonClicked(
            val context: Context
        ): Event()
    }

    sealed class Effect: UiEffect {
        data class MoveToSignup(
            val socialId: String
        ): Effect()
        object MoveToHome : Effect()
    }
}
