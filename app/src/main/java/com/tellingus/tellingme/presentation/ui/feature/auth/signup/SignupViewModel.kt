package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.model.oauth.signup.JoinRequestDto
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequest
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.usecase.JoinUserUseCase
import com.tellingus.tellingme.domain.usecase.VerifyNicknameUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val verifyNicknameUseCase: VerifyNicknameUseCase,
    private val joinUserUseCase: JoinUserUseCase,
): BaseViewModel<SignupContract.State, SignupContract.Event, SignupContract.Effect>(
    initialState = SignupContract.State()
) {
    fun initLoginInfo(socialId: String, socialLoginType: String) {
        updateState(currentState.copy(
            joinRequestDto = currentState.joinRequestDto.copy(
                socialId = socialId,
                socialLoginType = socialLoginType
            )
        ))
    }

    override fun reduceState(event: SignupContract.Event) {
        when(event) {
            is SignupContract.Event.NextButtonClickedInNickname -> {
                updateNickname(nickname = event.nickname)
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
                joinUser(currentState.joinRequestDto)
            }
        }
    }

    private fun joinUser(joinRequestDto: JoinRequestDto) {
        viewModelScope.launch {
            joinUserUseCase(
                joinRequestDto = joinRequestDto
            ).onSuccess {
                Log.d("taag joinUser", it.toString())
                postEffect(SignupContract.Effect.MoveToHome)
            }.onFailure { message, code ->
                Log.d("taag joinUser", message)
            }
        }
    }

    fun verifyNickname(nickname: String) {
        if (nickname.isEmpty()) {
            updateState(currentState.copy(nicknameErrorState = " "))
        } else if (nickname.length < 2) {
            updateState(currentState.copy(nicknameErrorState = "닉네임은 2~8글자여야 합니다."))
        } else if (" " in nickname) {
            updateState(currentState.copy(nicknameErrorState = "닉네임에 띄어쓰기가 포함될 수 없습니다."))
        } else if (!"^[가-힣]*$".toRegex().matches(nickname)) {
            updateState(currentState.copy(nicknameErrorState = "닉네임은 한글만 가능합니다. 영문과 숫자, 특수기호는 들어갈 수 없습니다."))
        } else {
            updateState(currentState.copy(nicknameErrorState = null))
        }
    }

    fun updateJobInfo(jobInfo: String) {
        viewModelScope.launch {
            updateState(
                currentState.copy(
                    joinRequestDto = currentState.joinRequestDto.copy(
                        jobInfo = jobInfo
                    )
                )
            )
        }
    }

    fun updateNickname(nickname: String) {
        viewModelScope.launch {
            updateState(currentState.copy(isLoading = true))

            verifyNicknameUseCase(
                nickname = nickname
            ).onSuccess {
                updateState(
                    currentState.copy(
                        isLoading = false,
                        nicknameErrorState = it.message
                    )
                )

                if (it == null) {
                }
            }.onFailure { message, code ->

            }.onNetworkError {

            }

            updateState(
                currentState.copy(
                    joinRequestDto = currentState.joinRequestDto.copy(
                        nickname = nickname
                    )
                )
            )
        }
    }

    private fun updateBirthGender(birth: String, gender: String) {
        viewModelScope.launch {
            updateState(
                currentState.copy(
                    joinRequestDto = currentState.joinRequestDto.copy(
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
                    joinRequestDto = currentState.joinRequestDto.copy(
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
                    joinRequestDto = currentState.joinRequestDto.copy(
                        purpose = worry.toString()
                    )
                )
            )
        }
    }
}