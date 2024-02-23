package com.tellingus.tellingme.presentation.ui.feature.home

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) {
    val state = HomeContract.State(
        isLoading = false,
        todayQuestionCardInfo = HomeContract.State.TodayQuestionCardInfo("", "")
    )


}