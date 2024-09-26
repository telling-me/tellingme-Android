package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import com.tellingus.tellingme.data.model.notice.LoadNotice
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class AlarmContract {

    data class State(
        val isLoading: Boolean = false,
        val isTotalRead: Boolean = false,
        val list: List<LoadNotice> = listOf(),
    ) : UiState

    sealed class Event : UiEvent {
      object OnClickTotalRead: Event()
    }

    sealed class Effect : UiEffect {}
}