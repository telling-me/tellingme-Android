package com.tellingus.tellingme.presentation.ui.feature.mypage

import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class MyPageContract {
    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        object OnClickSignOutButton: Event()
    }

    sealed class Effect : UiEffect {
        object MoveToLoginScreen: Effect()
    }
}
