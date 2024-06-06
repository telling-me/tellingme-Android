package com.tellingus.tellingme.presentation.ui.feature.mypage

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.usecase.SignOutUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
): BaseViewModel<MyPageContract.State, MyPageContract.Event, MyPageContract.Effect>(initialState = MyPageContract.State()) {
    fun signOutUser() {
        viewModelScope.launch {
            signOutUseCase().onSuccess {
                Log.d("taag s", it.toString())
            }.onFailure { m, c ->
                Log.d("taag f", c.toString())
            }
        }
    }
    override fun reduceState(event: MyPageContract.Event) {

    }
}