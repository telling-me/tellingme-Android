package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import com.tellingus.tellingme.data.model.notice.NoticeResponse
import com.tellingus.tellingme.presentation.ui.common.base.UiEffect
import com.tellingus.tellingme.presentation.ui.common.base.UiEvent
import com.tellingus.tellingme.presentation.ui.common.base.UiState

class AlarmContract {

    data class State(
        val isLoading: Boolean = false,
        val list: List<NoticeResponse> = listOf(),
    ) : UiState

    sealed class Event : UiEvent {}

    sealed class Effect : UiEffect {}
}