package com.tellingus.tellingme.presentation.ui.common.header

import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Header() {
    TopAppBar(
        title = { Text("헤더") },
        backgroundColor = Color.White
    )
}