package com.tellingus.tellingme.presentation.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class BUTTON_STATE {
    DEFAULT, HOVER, DISABLED, SELECTED
}

enum class BUTTON_SIZE {
    LARGE, MEDIUM, SMALL
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    state: BUTTON_STATE,
    size: BUTTON_SIZE,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick() },
        colors = when(state) {
            BUTTON_STATE.DEFAULT -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            BUTTON_STATE.HOVER -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            BUTTON_STATE.DISABLED -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            BUTTON_STATE.SELECTED -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
        },
        contentPadding = when(size) {
            BUTTON_SIZE.LARGE -> PaddingValues(vertical = 4.dp, horizontal = 8.dp)
            BUTTON_SIZE.MEDIUM -> PaddingValues(vertical = 4.dp, horizontal = 8.dp)
            BUTTON_SIZE.SMALL -> PaddingValues(vertical = 4.dp, horizontal = 8.dp)
        }
    ) {
        Text(
            text = text
        )
    }
}

@Composable
fun PrimaryLightButton() {

}

@Composable
fun SecondaryButton() {

}