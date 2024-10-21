package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import com.tellingus.tellingme.data.model.oauth.signup.SignUpRequest
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class SignupContract {
    data class State(
        val isLoading: Boolean = false,
        val isAvailableNickname: Boolean = false,
        val signupRequest: SignUpRequest = SignUpRequest(),
        val nicknameErrorState: String = ""
    ): UiState

    sealed class Event: UiEvent {
        data class NextButtonClickedInTerms(
            val nickname: String
        ): Event()

        data class NextButtonClickedInBirthGender(
            val birth: String,
            val gender: String
        ): Event()

        data class NextButtonClickedInJob(
            val job: Int
        ): Event()

        data class NextButtonClickedInWorry(
            val worry: List<Int>
        ): Event()

    }

    sealed class Effect: UiEffect {
        object ShowTermsBottomSheet: Effect()
        object MoveToBirthGender : Effect()
        object MoveToJob : Effect()
        object MoveToWorry : Effect()
        object CompleteSignup : Effect()
    }
}