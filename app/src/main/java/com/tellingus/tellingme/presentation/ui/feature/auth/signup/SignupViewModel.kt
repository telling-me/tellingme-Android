package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.data.model.oauth.signup.NicknameRequestDto
import com.tellingus.tellingme.data.network.adapter.onFailure
import com.tellingus.tellingme.data.network.adapter.onNetworkError
import com.tellingus.tellingme.data.network.adapter.onSuccess
import com.tellingus.tellingme.domain.repository.AuthRepository
import com.tellingus.tellingme.domain.usecase.VerifyNicknameUseCase
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val verifyNicknameUseCase: VerifyNicknameUseCase
): BaseViewModel<SignupContract.State, SignupContract.Event, SignupContract.Effect>(
    initialState = SignupContract.State()
) {

    fun initLoginInfo(socialId: String, socialLoginType: String) {
        viewModelScope.launch {
            updateState(currentState.copy(
                joinRequestDto = currentState.joinRequestDto.copy(
                    socialId = socialId,
                    socialLoginType = socialLoginType
                )
            ))
        }
    }

    override fun reduceState(event: SignupContract.Event) {
        when(event) {
            is SignupContract.Event.NextButtonClickedInNickname -> {
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

            }
        }
    }

    fun updateNickname(nickname: String) {
        viewModelScope.launch {
            updateState(currentState.copy(isLoading = true))

            verifyNicknameUseCase(
                nicknameRequestDto = NicknameRequestDto(nickname = nickname)
            )
                .onSuccess {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            nicknameErrorState = it.message
                        )
                    )

                    if (it == null) {
                        Log.d("taag", "tjdrhd")
                    }
                    Log.d("taag", it.toString())
                }.onFailure { message, code ->
                    Log.d("taag", message)
                    Log.d("taag", code.toString())
                }.onNetworkError {

                }

            updateState(
                currentState.copy(
                    joinRequestDto = currentState.joinRequestDto.copy(
                        nickname = nickname
                    ))
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


}