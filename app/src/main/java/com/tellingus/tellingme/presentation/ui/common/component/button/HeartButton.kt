package com.tellingus.tellingme.presentation.ui.common.component.button

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
import com.tellingus.tellingme.presentation.ui.common.model.ButtonState
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Error500
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun HeartButton(
    modifier: Modifier = Modifier,
    heartCount: Int,
    buttonState: ButtonState,
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
                    ButtonState.ENABLED, ButtonState.SELECTED -> Error500
                    ButtonState.DISABLED -> Gray200
                }
            )
        )
        Text(
            text = if (heartCount < 1000) heartCount.toString() else "999+",
            style = TellingmeTheme.typography.caption1Bold,
            color = when(buttonState) {
                ButtonState.ENABLED, ButtonState.SELECTED -> Gray600
                ButtonState.DISABLED -> Gray300
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
            buttonState = ButtonState.ENABLED,
            onClick = {}
        )
        HeartButton(
            heartCount = 72,
            buttonState = ButtonState.SELECTED,
            onClick = {}
        )
        HeartButton(
            heartCount = 1234,
            buttonState = ButtonState.DISABLED,
            onClick = {}
        )
    }
}