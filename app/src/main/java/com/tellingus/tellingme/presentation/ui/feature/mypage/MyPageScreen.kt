package com.tellingus.tellingme.presentation.ui.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyPageScreen(navigateToAlarmScreen: () -> Unit) {
    Column {
        Text(text = "마이 페이지")
        Button(onClick = { navigateToAlarmScreen() }) {
            Text(text = "알람페이지 이동")
        }
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(navigateToAlarmScreen = {})
}