package com.tellingus.tellingme.presentation.ui.feature.main.otherSpace.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun OtherSpaceDetailScreen(navigateToOtherSpaceScreen: () -> Unit) {
    Column {

        Text(text = "모두의 공간 상세")

        Button(onClick = { navigateToOtherSpaceScreen() }) {
            Text("모두의 공간으로 이동")
        }
    }


}