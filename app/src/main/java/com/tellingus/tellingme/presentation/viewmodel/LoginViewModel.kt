package com.tellingus.tellingme.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.model.login.OauthRequestDto
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.usecase.LoginUseCase
import com.tellingus.tellingme.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {



    fun loginFromKakao(
        oauthToken: String,
        loginType: String = LoginType.KAKAO.name,
        isAuto: String,
        oauthRequestDto: OauthRequestDto
    ) {
        viewModelScope.launch {
            loginUseCase(
                oauthToken = oauthToken,
                loginType = loginType.lowercase(),
                isAuto = isAuto.lowercase(),
                oauthRequestDto = oauthRequestDto
            )
                .onSuccess { it, code ->
                    when(code) {
                        200 -> {
                            Log.d(TAG, it.toString())
                        }
                    }
                }
                .onFailure { message, code ->
                    when(code) {
                        404 -> {
                            Log.d(TAG, message)
                            Log.d(TAG, message.split("${'"'}")[3])
                        }
                        1000 -> {
                            Log.d(TAG, code.toString())
                        }
                        1001 -> {
                            Log.d(TAG, code.toString())
                        }
                    }
                }
                .onNetworkError {

                }

        }
    }
}

enum class LoginType(name: String) {
    KAKAO("kakao")
}
enum class IsAuto(name: String) {
    MANUAL("manual"),
    AUTO("auto")
}