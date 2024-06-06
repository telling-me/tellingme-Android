package com.tellingus.tellingme.presentation.ui.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.presentation.ui.common.navigation.MyPageDestinations

@Composable
fun MyPageScreen(
    navController: NavController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    Column {
        Text(text = "마이 페이지")
        Button(onClick = {
//            navigateToAlarmScreen()
            navController.navigate(MyPageDestinations.ALARM)
        }) {
            Text(text = "알람페이지 이동")
        }

        Button(
            onClick = {
                viewModel.signOutUser()
            }
        ) {
            Text(text = "유저 탈퇴하기")
        }
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(navController = rememberNavController())
}