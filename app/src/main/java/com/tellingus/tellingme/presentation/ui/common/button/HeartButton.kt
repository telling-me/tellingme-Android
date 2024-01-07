package com.tellingus.tellingme.presentation.ui.common.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Red100
import com.tellingus.tellingme.presentation.ui.theme.Red500
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun HeartButton(
    modifier: Modifier = Modifier,
    heartCount: Int,
    buttonState: BUTTON_STATE,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.icon_heart),
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                when(buttonState) {
                    BUTTON_STATE.ENABLED, BUTTON_STATE.SELECTED -> Red500
                    BUTTON_STATE.DISABLED -> Gray200
                }
            )
        )
        Text(
            text = if (heartCount < 1000) heartCount.toString() else "999+",
            style = TellingmeTheme.typography.caption1Bold,
            color = when(buttonState) {
                BUTTON_STATE.ENABLED, BUTTON_STATE.SELECTED -> Gray600
                BUTTON_STATE.DISABLED -> Gray300
            }
        )
    }
}

@Preview
@Composable
fun HeartButtonPreview() {
    Column {
        HeartButton(
            heartCount = 123,
            buttonState = BUTTON_STATE.ENABLED,
            onClick = {}
        )
        HeartButton(
            heartCount = 72,
            buttonState = BUTTON_STATE.SELECTED,
            onClick = {}
        )
        HeartButton(
            heartCount = 1234,
            buttonState = BUTTON_STATE.DISABLED,
            onClick = {}
        )
    }
}