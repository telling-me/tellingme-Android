package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import android.util.Log
import com.tellingus.tellingme.presentation.ui.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(

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
                postEffect(SignupContract.Effect.MoveToBirthGender)
            }
            is SignupContract.Event.NextButtonClickedInBirthGender -> {

            }
            is SignupContract.Event.NextButtonClickedInJob -> {

            }
            is SignupContract.Event.NextButtonClickedInWorry -> {

            }
        }
    }

    fun checkNickname(nickname: String) {

    }


}