package com.tellingus.tellingme.presentation.ui.feature.auth.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.tellingus.tellingme.data.model.oauth.login.OauthRequestDto
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import com.tellingus.tellingme.domain.usecase.LoginUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import com.tellingus.tellingme.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val loginUseCase: LoginUseCase,
): BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    initialState = LoginContract.State()
) {
    init {
        // 로그인 여부 확인하는 작업을 여기서 할듯?

    }

    override fun reduceState(event: LoginContract.Event) {
        viewModelScope.launch {
            when (event) {
                is LoginContract.Event.KakaoLoginButtonClicked -> {
                    kakaoLogin(event.context)
                }
                is LoginContract.Event.MoveToHomeButtonClicked -> {
                    postEffect(LoginContract.Effect.MoveToHome)
                }
            }
        }
    }

    private fun kakaoLogin(context: Context) {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                loginFromKakao(token.accessToken)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    loginFromKakao(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    private fun loginFromKakao(oauthToken: String) {
        viewModelScope.launch {
            postEffect(LoginContract.Effect.MoveToSignup(socialId = "1"))
            updateState(currentState.copy(isLoading = true))
            loginUseCase(
                oauthToken = oauthToken,
                loginType = LoginType.KAKAO.name.lowercase(),
                isAuto = IsAuto.MANUAL.name.lowercase(),
                oauthRequestDto = OauthRequestDto()
            )
                .onSuccess {
                    // 자동 로그인인 경우 바로 홈 화면으로 진입
                    updateState(currentState.copy(isLoading = true))
                    postEffect(LoginContract.Effect.MoveToHome)
                }
                .onFailure { message, code ->
                    when(code) {
                        404 -> {
                            // 최초 로그인이라면 로컬에 socialId 저장 후 추가정보 기입 화면으로 이동
                            val socialId = message.split("${'"'}")[3]
                            Log.d(TAG, "$message // socialId : $socialId")

                            // 소셜로그인 결과 404라면 추가정보 기입 화면으로 이동
                            dataStoreRepository.setUserSocialId(socialId)
                            postEffect(LoginContract.Effect.MoveToSignup(socialId = socialId))
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
                    Log.e(TAG, "Network Error")
                }
        }
    }
}

enum class IsAuto(name: String) {
    MANUAL("manual"),
    AUTO("auto")
}

enum class LoginType(name: String) {
    KAKAO("kakao")
}