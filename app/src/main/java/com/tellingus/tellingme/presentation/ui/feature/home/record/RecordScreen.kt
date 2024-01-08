package com.tellingus.tellingme.presentation.ui.feature.home.record

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RecordScreen(navigateToPreviousScreen: () -> Unit) {
    Column {

        Text(text = "기록하기 화면입니다")

        Button(onClick = { navigateToPreviousScreen() }) {
            Text("이전")
        }
    }
}