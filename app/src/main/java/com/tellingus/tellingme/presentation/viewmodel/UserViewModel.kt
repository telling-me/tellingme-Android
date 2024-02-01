package com.tellingus.tellingme.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.model.login.OauthRequestDto
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.data.repositoryimpl.DataStoreRepositoryImpl
import com.tellingus.tellingme.domain.usecase.LoginUseCase
import com.tellingus.tellingme.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dataStoreRepositoryImpl: DataStoreRepositoryImpl,
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
                            val socialId = message.split("${'"'}")[3]
                            Log.d(TAG, "$message // socialId : $socialId")

                            // 소셜로그인 로컬에 저장하고 읽기
                            CoroutineScope(Dispatchers.IO).launch {
                                dataStoreRepositoryImpl.setUserSocialId(socialId)
                                withContext(Dispatchers.IO) {
                                    dataStoreRepositoryImpl.getUserSocialId().collectLatest {
                                        Log.d(TAG+"1", it)
                                    }
                                }
                            }
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

    private fun saveUserSocialId(socialId: String) {
        viewModelScope.launch {
            dataStoreRepositoryImpl.setUserSocialId(socialId)
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