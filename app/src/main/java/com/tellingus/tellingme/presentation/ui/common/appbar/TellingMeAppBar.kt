package com.tellingus.tellingme.presentation.ui.common.appbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * AppBar 컴포즈 정의
 */
@Composable
fun TellingMeAppBar(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(44.dp)) {
        Text(text = title)
    }
}