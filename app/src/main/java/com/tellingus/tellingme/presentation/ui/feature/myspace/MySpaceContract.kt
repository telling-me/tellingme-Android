package com.tellingus.tellingme.presentation.ui.feature.myspace

import android.content.Context
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState
import java.time.LocalDate

class MySpaceContract {
    data class State(
        val isLoading: Boolean = false,
        val today: LocalDate = LocalDate.now(),
        val currentDate: LocalDate = LocalDate.now()
    ): UiState

    sealed class Event: UiEvent {
        object OnClickTodayButton: Event()
        data class UpdateCurrentDate(
            val swipe: Long
        ): Event()

        data class OnClickCalendarDate(
            val year: String,
            val month: String
        ): Event()
    }

    sealed class Effect: UiEffect {
        object ScrollToToday: Effect()
        object ShowAnswerListPagerDialog: Effect()
        object ShowAnswerEmptyDialog: Effect()
    }
}
