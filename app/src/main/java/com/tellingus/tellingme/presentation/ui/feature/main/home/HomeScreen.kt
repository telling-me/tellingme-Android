package com.tellingus.tellingme.presentation.ui.feature.main.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navigateToOtherSpace: (name: String) -> Unit
) {
    Text(text = "홈")

    Button(onClick = { navigateToOtherSpace("name") }) {
        Text(text = "더보기")
    }
}