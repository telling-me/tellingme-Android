package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
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
    Column(
        modifier = modifier
            .fillMaxHeight()
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
        Spacer(modifier = Modifier.size(4.dp))


    }
}

