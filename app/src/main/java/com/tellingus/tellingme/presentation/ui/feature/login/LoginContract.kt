package com.tellingus.tellingme.presentation.ui.feature.login

import com.tellingus.tellingme.data.model.oauth.response.JoinRequestDto
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    val joinRequestState: StateFlow<JoinRequestDto>
    val loginUiEffect: SharedFlow<LoginUiEffect>
}

sealed class LoginUiEffect {
    data class MoveToOauthJoin(
        val socialId: String
    ): LoginUiEffect()

    object MoveToHome : LoginUiEffect()
}