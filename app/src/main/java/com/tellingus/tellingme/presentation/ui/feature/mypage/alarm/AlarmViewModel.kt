package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.usecase.notice.LoadNoticeUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val loadNoticeUseCase: LoadNoticeUseCase
) : BaseViewModel<AlarmContract.State, AlarmContract.Event, AlarmContract.Effect>(
    initialState = AlarmContract.State()
) {
    val TAG: String = "로그"

    init {
        // loadAlarmList()
    }

    override fun reduceState(event: AlarmContract.Event) {
        when (event) {
            is AlarmContract.Event.OnClickTotalRead -> {
                postReadAll()
            }
        }
    }

    // TODO: 전체읽음 API
    private fun postReadAll() {
        Log.d(TAG, "postReadAll: ")
    }

    fun loadAlarmList() {
        viewModelScope.launch {
            loadNoticeUseCase().onSuccess { it ->
                currentState.copy(isLoading = true, list = it)
            }.onFailure { message, code ->
                currentState.copy(isLoading = false)
                val TAG: String = "로그"
                Log.d(
                    TAG,
                    "AlarmViewModel - loadAlarmList() called Error message: $message, code: $code"
                )
            }
        }
    }

}