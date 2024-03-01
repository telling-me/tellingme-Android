package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
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
import com.tellingus.tellingme.presentation.ui.common.component.button.SingleBlackButton
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
fun SignupBirthGenderScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    MainLayout(
        header = {
            BasicAppBar(
                modifier = modifier
                    .fillMaxWidth(),
                leftSlot = {
                    TellingmeIconButton(
                        modifier = modifier
                            .padding(all = 12.dp),
                        iconRes = R.drawable.icon_caret_left,
                        size = ButtonSize.MEDIUM,
                        color = Gray500,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                },
                rightSlot = {
                    SingleBlackButton(
                        modifier = modifier
                            .padding(end = 10.dp),
                        size = ButtonSize.MEDIUM,
                        text = "건너뛰기",
                        onClick = {  }
                    )
                }
            )
        },
        content = {
            SignupBirthGenderContentScreen(
                navController = navController,
                viewModel = viewModel
            )
        },
        isScrollable = false,
        background = Base0
    )

}

@Composable
fun SignupBirthGenderContentScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isEnableUseNickname by remember { mutableStateOf(false) }
    var nickname by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    viewModel.effect.collectWithLifecycle { effect ->
        when(effect) {
            is SignupContract.Effect.MoveToJob -> {
                navController.navigate(AuthDestinations.Signup.SIGNUP_BIRTH_GENDER)
            }
            else -> {}
        }
    }

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
                text = "출생연도와 성별을 알려주세요",
                style = TellingmeTheme.typography.head2Bold,
                color = Gray600
            )
            Spacer(modifier = Modifier.size(55.dp))

            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                value = nickname,
                onValueChange = {
                    if (it.length <=4) {
                        nickname = it

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
                                    text = if (nickname.isBlank()) "2-8자 이내 (영문, 숫자, 특수문자 제외)" else " ",
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
                                                nickname = ""
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
                        Text(
                            modifier = modifier
                                .clickable(
                                    enabled = false,
                                    onClick = {}
                                ),
                            text = "한글만 입력가능합니다.",
                            style = TellingmeTheme.typography.caption1Regular.copy(
                                color = Error600
                            )
                        )
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
                viewModel.processEvent(SignupContract.Event.NextButtonClickedInBirthGender)
            }
        )
    }
}

@Preview
@Composable
fun SignupBirthGenderScreenPreview() {
    SignupBirthGenderScreen(navController = rememberNavController())
}