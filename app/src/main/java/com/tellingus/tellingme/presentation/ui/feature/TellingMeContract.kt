package com.tellingus.tellingme.presentation.ui.feature

import kotlinx.coroutines.flow.SharedFlow

interface TellingMeContract {
    val tellingMeUiEffect: SharedFlow<TellingMeUiEffect>

}

sealed class TellingMeUiEffect {
    object MoveToLogin: TellingMeUiEffect()

}
