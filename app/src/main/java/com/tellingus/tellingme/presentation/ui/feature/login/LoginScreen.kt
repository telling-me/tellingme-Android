package com.tellingus.tellingme.presentation.ui.feature.login

import android.util.Log
import android.widget.Toast
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
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.util.TAG
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    MainLayout(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
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
                Spacer(modifier = Modifier.size(20.dp))
                PrimaryButton(
                    size = ButtonSize.LARGE,
                    text = "홈 화면으로 이동",
                    onClick = {
                        navController.navigate(HomeDestinations.HOME) {
                            popUpTo(AuthDestinations.ROUTE) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        },
        isScrollable = false
    )

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.MoveToOauthJoin -> {
                    Toast.makeText(context, "최초로그인 -> 상세정보 기입 화면 이동",Toast.LENGTH_SHORT).show()
                }
                is LoginContract.Effect.MoveToHome -> {
                    Toast.makeText(context, "자동로그인 -> 홈 화면 이동", Toast.LENGTH_SHORT).show()
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