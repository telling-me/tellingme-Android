package com.tellingus.tellingme.presentation.ui.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.data.repositoryimpl.HomeRepositoryImpl
import com.tellingus.tellingme.domain.repository.HomeRepository
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import com.tellingus.tellingme.util.getToday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.Effect>(
    initialState = HomeContract.State()
) {
    init {
        viewModelScope.launch {
            homeRepository.getQuestion(getToday())
                .onSuccess {
                    updateState(
                        currentState.copy(

                        )
                    )
                    Log.d("taag", it.toString())
                }
                .onFailure { s, i ->

                }
        }
    }

    val state = HomeContract.State(
        isLoading = false,
        todayQuestionCardInfo = HomeContract.State.TodayQuestionCardInfo("", "")
    )

    override fun reduceState(event: HomeContract.Event) {
    }

    fun getNotice() {
//        viewModelScope.launch {
//            val notice = homeRepository.getNotice()
//                .onSuccess {
//                    Log.d("taag homeViewModel s", it.toString())
//                }
//                .onFailure { s, i ->
//                    Log.d("taag homeViewModel f", "$s - $i")
//                }
//        }
    }

//    fun getQuestion() {
//        viewModelScope.launch {
//            homeRepository.getQuestion()
//        }
//    }
}