package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import com.tellingus.tellingme.domain.usecase.notice.LoadNoticeUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val loadNoticeUseCase: LoadNoticeUseCase
) : BaseViewModel<AlarmContract.State, AlarmContract.Event, AlarmContract.Effect>(
    initialState = AlarmContract.State()
) {
    override fun reduceState(event: AlarmContract.Event) {
        TODO("Not yet implemented")
    }

}