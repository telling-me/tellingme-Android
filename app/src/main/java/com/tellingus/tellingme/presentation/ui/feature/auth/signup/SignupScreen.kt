package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun SignupScreen(
    navController: NavController,
    socialId: String,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    viewModel.initLoginInfo(socialId = socialId, socialLoginType = "kakao")

    MainLayout(
        header = {
            BasicAppBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                leftSlot = {
                    TellingmeIconButton(
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
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, bottom = 16.dp, start = 20.dp, end = 20.dp)
            ) {
                SignupStep1Screen(
                    navController = navController,
                    viewModel = viewModel,
                )
                PrimaryButton(
                    modifier = modifier
                        .fillMaxWidth(),
                    size = ButtonSize.LARGE,
                    text = "다음",
                    onClick = { }
                )
            }
        },
        isScrollable = false
    )

}

@Composable
fun ColumnScope.SignupStep1Screen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var nickname by remember { mutableStateOf("") }

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
                .fillMaxWidth(),
            value = nickname,
            onValueChange = { nickname = it },
            textStyle = TellingmeTheme.typography.body1Regular.copy(
                color = Gray600,
                textAlign = TextAlign.Start
            ),
            maxLines = 1,
            singleLine = true,
            decorationBox = { innerTextField ->
                Box {
                    Column {
                        Text(
                            modifier = modifier,
                            text = if (nickname.isBlank()) "2-8자 이내 (영문, 숫자, 특수문자 제외)" else "",
                            style = TellingmeTheme.typography.body1Regular.copy(
                                color = Gray300
                            )
                        )
                        Spacer(modifier = modifier.size(8.dp))
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(Gray200)
                                .height(1.dp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen(navController = rememberNavController(), socialId = "")
}