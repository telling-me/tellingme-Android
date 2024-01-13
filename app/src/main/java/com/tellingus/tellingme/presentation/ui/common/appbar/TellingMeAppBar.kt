package com.tellingus.tellingme.presentation.ui.common.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * AppBar 컴포즈 정의
 */
@Composable
fun TellingMeAppBar( // 제거 되도될것같음
    modifier: Modifier = Modifier, title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red)
            .height(44.dp)
    ) {
        Text(text = title)
    }
}

@Composable
fun BasicAppBar(
    modifier: Modifier = Modifier
        .background(Color.White)
        .height(44.dp)
        .padding(start = 8.dp, end = 8.dp)
        .fillMaxWidth(),
    leftSlot: @Composable (() -> Unit)? = null, rightSlot: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftSlot?.let { it() }
        rightSlot?.let { it() }
    }
}