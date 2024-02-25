package com.tellingus.tellingme.presentation.ui.feature.home

import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(initialState = HomeContract.State()) {
    val state = HomeContract.State(
        isLoading = false,
        todayQuestionCardInfo = HomeContract.State.TodayQuestionCardInfo("", "")
    )

    override fun reduceState(event: HomeContract.Event) {
        TODO("Not yet implemented")
    }
}