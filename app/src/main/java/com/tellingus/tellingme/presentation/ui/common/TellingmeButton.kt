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
import com.tellingus.tellingme.presentation.ui.theme.Red50
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.presentation.ui.theme.Typography

enum class BUTTON_TYPE {
    DEFAULT, HOVER, DISABLED, SELECTED
}

enum class BUTTON_SIZE {
    LARGE, MEDIUM, SMALL
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    state: BUTTON_TYPE,
    size: BUTTON_SIZE,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick() },
        colors = when(state) {
            BUTTON_TYPE.DEFAULT -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            BUTTON_TYPE.HOVER -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            BUTTON_TYPE.DISABLED -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            BUTTON_TYPE.SELECTED -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
        },
        contentPadding = when(size) {
            BUTTON_SIZE.LARGE -> PaddingValues(vertical = 4.dp, horizontal = 8.dp)
            BUTTON_SIZE.MEDIUM -> PaddingValues(vertical = 4.dp, horizontal = 8.dp)
            BUTTON_SIZE.SMALL -> PaddingValues(vertical = 4.dp, horizontal = 8.dp)
        }
    ) {
        Text(
            text = text,
//            style = Typography.h1Regular.copy(
            style = TellingmeTheme.typography.h1Regular.copy(
                color = Red50
            )
        )
    }
}

@Composable
fun PrimaryLightButton() {

}

@Composable
fun SecondaryButton() {

}