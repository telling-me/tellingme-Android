package com.tellingus.tellingme.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.model.login.OauthRequestDto
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import com.tellingus.tellingme.domain.usecase.LoginUseCase
import com.tellingus.tellingme.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
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
                            // 최초 로그인이라면 로컬에 socialId 저장 후 추가정보 기입 화면으로 이동
                            val socialId = message.split("${'"'}")[3]
                            Log.d(TAG, "$message // socialId : $socialId")

                            // 소셜로그인 결과 404라면
                            viewModelScope.launch {
                                dataStoreRepository.setUserSocialId(socialId)
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
}
