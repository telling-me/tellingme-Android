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
import com.tellingus.tellingme.data.model.login.LoginRequestBody
import com.tellingus.tellingme.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    fun loginFromKakao(
        oauthToken: String,
        loginType: String = LOGIN_TYPE.KAKAO.name,
        isAuto: String,
        loginRequestBody: LoginRequestBody
    ) {
        viewModelScope.launch {
            val response = loginUseCase(
                oauthToken = oauthToken,
                loginType = loginType,
                isAuto = isAuto,
                loginRequestBody = loginRequestBody
            )

            Log.d("taag", response.code().toString())
        }
    }


}

enum class LOGIN_TYPE(name: String) {
    KAKAO("kakao")
}
enum class IS_AUTO(name: String) {
    MANUAL("manual"),
    AUTO("auto")
}