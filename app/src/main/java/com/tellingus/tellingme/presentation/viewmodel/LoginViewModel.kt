package com.tellingus.tellingme.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.tellingus.tellingme.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    val testValue = MutableStateFlow<Int>(0)

    fun loginFromKakao(token: String) {
        viewModelScope.launch {
            val response = loginUseCase()
            Log.d("taag", token)
        }
    }

    fun plusTestValue() {
        testValue.value = testValue.value + 1
    }

}
