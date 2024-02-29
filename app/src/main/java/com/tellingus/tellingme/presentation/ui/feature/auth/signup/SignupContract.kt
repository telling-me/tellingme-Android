package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class SignupContract {
    data class State(
        val signupContents: OauthRequestDto = OauthRequestDto()
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