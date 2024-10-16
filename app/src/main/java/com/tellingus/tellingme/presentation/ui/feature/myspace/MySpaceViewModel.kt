package com.tellingus.tellingme.presentation.ui.feature.myspace

import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import com.tellingus.tellingme.domain.repository.MySpaceRepository
import com.tellingus.tellingme.domain.usecase.GetAnswerListUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MySpaceViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val getAnswerListUseCase: GetAnswerListUseCase
): BaseViewModel<MySpaceContract.State, MySpaceContract.Event, MySpaceContract.Effect>(
    initialState = MySpaceContract.State()
) {

    init {
        viewModelScope.launch {
            getAnswerListUseCase(year = "2024", month = "09")
        }
    }

    override fun reduceState(event: MySpaceContract.Event) {
        when(event) {
            is MySpaceContract.Event.OnClickTodayButton -> {
                postEffect(MySpaceContract.Effect.ScrollToToday)
            }

            is MySpaceContract.Event.UpdateCurrentDate -> {
                updateState(
                    currentState.copy(
                        currentDate = currentState.currentDate.plusMonths(event.swipe)
                    )
                )
            }

            is MySpaceContract.Event.OnClickCalendarDate -> {
                // 해당 날짜에 답변 있는지 없는지 확인해야댐
                postEffect(MySpaceContract.Effect.ShowAnswerListPagerDialog)
                postEffect(MySpaceContract.Effect.ShowAnswerEmptyDialog)
            }
        }
    }


}
