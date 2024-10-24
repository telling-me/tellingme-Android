package com.tellingus.tellingme.presentation.ui.feature.auth.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.presentation.ui.common.component.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.component.dialog.PushAlertDialog
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.util.collectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    MainLayout(
        content = {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PrimaryButton(
                    size = ButtonSize.LARGE,
                    text = "카카오 로그인",
                    onClick = {
                        viewModel.processEvent(LoginContract.Event.KakaoLoginButtonClicked(context))
                    }
                )
                Spacer(modifier = modifier.size(20.dp))
                PrimaryButton(
                    size = ButtonSize.LARGE,
                    text = "홈 화면으로 이동",
                    isScaleDown = true,
                    onClick = {
                        viewModel.processEvent(LoginContract.Event.MoveToHomeButtonClicked)
                    }
                )
            }
        },
        isScrollable = false
    )

    viewModel.effect.collectWithLifecycle { effect ->
        when (effect) {
            is LoginContract.Effect.MoveToSignup -> {
                navController.navigate("${AuthDestinations.Signup.SIGNUP_NICKNAME}/${effect.socialId}")
            }
            is LoginContract.Effect.MoveToHome -> {
                navController.navigate(HomeDestinations.HOME) {
                    popUpTo(AuthDestinations.ROUTE) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}