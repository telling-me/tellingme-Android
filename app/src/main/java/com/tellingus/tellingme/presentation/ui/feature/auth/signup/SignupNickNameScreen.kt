package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Error600
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.util.collectWithLifecycle

@Composable
fun SignupNicknameScreen(
    navController: NavController,
    socialId: String,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.initLoginInfo(socialId = socialId, socialLoginType = "kakao")
    }

    MainLayout(
        header = {
            BasicAppBar(
                modifier = modifier
                    .fillMaxWidth(),
                leftSlot = {
                    TellingmeIconButton(
                        modifier = modifier
                            .padding(12.dp),
                        iconRes = R.drawable.icon_caret_left,
                        size = ButtonSize.MEDIUM,
                        color = Gray500,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                }
            )
        },
        content = {
            SignupNicknameContentScreen(
                navController = navController,
                viewModel = viewModel
            )
        },
        isScrollable = false,
        background = Base0
    )

}

@Composable
fun SignupNicknameContentScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isFocused by remember { mutableStateOf(false) }

    Log.d("taag nick", uiState.joinRequestDto.toString())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 16.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            Text(
                text = "닉네임을 입력해주세요",
                style = TellingmeTheme.typography.head2Bold,
                color = Gray600
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "닉네임은 이후에도 변경 가능해요",
                style = TellingmeTheme.typography.body2Regular,
                color = Gray600
            )
            Spacer(modifier = Modifier.size(30.dp))

            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                value = uiState.joinRequestDto.nickname,
                onValueChange = {
                    if (it.length <=8) {
                        viewModel.updateNickname(it)
                        // 닉네임 관련 API 쏘기

                    }
                },
                textStyle = TellingmeTheme.typography.body1Regular.copy(
                    color = Gray600,
                    textAlign = TextAlign.Start
                ),
                maxLines = 1,
                singleLine = true,
                decorationBox = { innerTextField ->
                    Column {
                        Box {
                            Box(
                                modifier = modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                                Text(
                                    text = if (uiState.joinRequestDto.nickname.isBlank()) "2-8자 이내 (영문, 숫자, 특수문자 제외)" else " ",
                                    style = TellingmeTheme.typography.body1Regular.copy(
                                        color = Gray300
                                    )
                                )
                                Icon(
                                    modifier = modifier
                                        .align(Alignment.CenterEnd)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            onClick = {
                                                viewModel.updateNickname("")
                                            }
                                        ),
                                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_clear_text),
                                    contentDescription = null,
                                    tint = Gray300
                                )
                            }
                        }
                        Spacer(modifier = modifier.size(8.dp))
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(if (isFocused) Primary400 else Gray200)
                                .height(1.dp)

                        )
                        Spacer(modifier = modifier.size(8.dp))
                        uiState.nicknameErrorState?.let {
                            Text(
                                modifier = modifier
                                    .clickable(
                                        enabled = false,
                                        onClick = {}
                                    ),
                                text = it,
                                style = TellingmeTheme.typography.caption1Regular.copy(
                                    color = Error600
                                )
                            )
                        }

                    }
                }
            )
        }
        PrimaryButton(
            modifier = modifier
                .fillMaxWidth(),
            size = ButtonSize.LARGE,
            text = "다음",
//            enable = isEnableUseNickname,
            onClick = {
                viewModel.processEvent(SignupContract.Event.NextButtonClickedInNickname)
            }
        )
    }

    viewModel.effect.collectWithLifecycle { effect ->
        when(effect) {
            is SignupContract.Effect.MoveToBirthGender -> {
                navController.navigate(AuthDestinations.Signup.SIGNUP_BIRTH_GENDER)
            }
            else -> {}
        }
    }
}

@Preview
@Composable
fun SignupNicknameScreenPreview() {
    SignupNicknameScreen(navController = rememberNavController(), socialId = "")
}