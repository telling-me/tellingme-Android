package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.model.oauth.signup.SignupRequest
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.usecase.SignupUseCase
import com.tellingus.tellingme.domain.usecase.VerifyNicknameUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val verifyNicknameUseCase: VerifyNicknameUseCase,
    private val signupUseCase: SignupUseCase,
): BaseViewModel<SignupContract.State, SignupContract.Event, SignupContract.Effect>(
    initialState = SignupContract.State()
) {
    fun initLoginInfo(socialId: String, socialLoginType: String) {
        updateState(currentState.copy(
            signupRequest = currentState.signupRequest.copy(
                socialId = socialId,
                socialLoginType = socialLoginType
            )
        ))
    }

    override fun reduceState(event: SignupContract.Event) {
        when(event) {
            is SignupContract.Event.NextButtonClickedInTerms -> {
                postEffect(SignupContract.Effect.MoveToBirthGender)
            }
            is SignupContract.Event.NextButtonClickedInBirthGender -> {
                updateBirthGender(birth = event.birth, gender = event.gender)
                postEffect(SignupContract.Effect.MoveToJob)
            }
            is SignupContract.Event.NextButtonClickedInJob -> {
                updateJob(job = event.job)
                postEffect(SignupContract.Effect.MoveToWorry)
            }
            is SignupContract.Event.NextButtonClickedInWorry -> {
                updateWorry(worry = event.worry)
            }
        }
    }

    private fun signup(signupRequest: SignupRequest) {
        viewModelScope.launch {
            signupUseCase(
                signupRequest = signupRequest
            ).onSuccess {
                Log.d("taag joinUser", it.toString())
                postEffect(SignupContract.Effect.CompleteSignup)
            }.onFailure { message, code ->
                Log.d("taag joinUser", message)
            }
        }
    }

    fun verifyNicknameFormat(nickname: String) {
        updateState(currentState.copy(isAvailableNickname = false))
        if (nickname.isEmpty()) {
            updateState(currentState.copy(nicknameErrorState = ""))
        } else if (nickname.length < 2) {
            updateState(currentState.copy(nicknameErrorState = "닉네임은 2~8글자여야 합니다."))
        } else if (" " in nickname) {
            updateState(currentState.copy(nicknameErrorState = "닉네임에 띄어쓰기가 포함될 수 없습니다."))
        } else if (!"^[가-힣]*$".toRegex().matches(nickname)) {
            updateState(currentState.copy(nicknameErrorState = "닉네임은 한글만 가능합니다. 영문과 숫자, 특수기호는 들어갈 수 없습니다."))
        } else {
            updateState(currentState.copy(nicknameErrorState = "정상"))
        }
    }

    fun updateJobInfo(jobInfo: String) {
        viewModelScope.launch {
            updateState(
                currentState.copy(
                    signupRequest = currentState.signupRequest.copy(
                        jobInfo = jobInfo
                    )
                )
            )
        }
    }

    fun verifyNickname(nickname: String) {
        viewModelScope.launch {
            verifyNicknameUseCase(
                nickname = nickname
            ).onSuccess {
                if (it.data) {
                    updateState(
                        currentState.copy(
                            isAvailableNickname = true,
                            signupRequest = currentState.signupRequest.copy(
                                nickname = nickname
                            )
                        )
                    )
                    postEffect(SignupContract.Effect.ShowTermsBottomSheet)
                } else {
                    updateState(
                        currentState.copy(
                            isAvailableNickname = false,
                            nicknameErrorState = it.message
                        )
                    )
                }
            }.onFailure { message, code ->

            }.onNetworkError {

            }
        }
    }

    private fun updateBirthGender(birth: String, gender: String) {
        viewModelScope.launch {
            updateState(
                currentState.copy(
                    signupRequest = currentState.signupRequest.copy(
                        birthDate = birth,
                        gender = gender
                    )
                )
            )
        }
    }

    private fun updateJob(job: Int) {
        viewModelScope.launch {
            updateState(
                currentState.copy(
                    signupRequest = currentState.signupRequest.copy(
                        job = job
                    )
                )
            )
        }
    }

    private fun updateWorry(worry: List<Int>) {
        viewModelScope.launch {
            updateState(
                currentState.copy(
                    signupRequest = currentState.signupRequest.copy(
                        purpose = worry.toString()
                    )
                )
            )
            signup(currentState.signupRequest)
        }
    }
}