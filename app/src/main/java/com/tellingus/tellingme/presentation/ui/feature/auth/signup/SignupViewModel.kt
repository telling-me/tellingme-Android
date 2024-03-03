package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import androidx.lifecycle.viewModelScope
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(

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
            updateState(
                currentState.copy(
                    joinRequestDto = currentState.joinRequestDto.copy(
                        nickname = nickname
                    ))
            )

            updateState(
                currentState.copy(
                    nicknameErrorState = if (nickname.length<5) "5자 이상 에러!" else null
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


}