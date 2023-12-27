package com.tellingus.tellingme.presentation.ui.common.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.Primary500
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

enum class BUTTON_SIZE {
    LARGE, MEDIUM, SMALL
}

enum class BUTTON_STATE {
    DEFAULT, HOVER, DISABLED, SELECTED
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
            BUTTON_STATE.DEFAULT -> ButtonDefaults.buttonColors(containerColor = Primary400)
            BUTTON_STATE.HOVER -> ButtonDefaults.buttonColors(containerColor = Primary500)
            BUTTON_STATE.DISABLED -> ButtonDefaults.buttonColors(containerColor = Primary500)
            BUTTON_STATE.SELECTED -> ButtonDefaults.buttonColors(containerColor = Primary400)
        },
        contentPadding = when(size) {
            BUTTON_SIZE.LARGE -> PaddingValues(
                vertical = dimensionResource(R.dimen.button_padding_vertical_large),
                horizontal = dimensionResource(R.dimen.button_padding_horizontal_large)
            )
            BUTTON_SIZE.MEDIUM -> PaddingValues(
                vertical = dimensionResource(R.dimen.button_padding_vertical_medium),
                horizontal = dimensionResource(R.dimen.button_padding_horizontal_medium)
            )
            BUTTON_SIZE.SMALL -> PaddingValues(
                vertical = dimensionResource(R.dimen.button_padding_vertical_small),
                horizontal = dimensionResource(R.dimen.button_padding_horizontal_small)
            )
        }
    ) {
        Text(
            text = text,
            style = when(size) {
                BUTTON_SIZE.LARGE -> TellingmeTheme.typography.h1Regular
                BUTTON_SIZE.MEDIUM -> TellingmeTheme.typography.h1Regular
                BUTTON_SIZE.SMALL -> TellingmeTheme.typography.h1Regular
            },
            color = Base0
//            style = TellingmeTheme.typography.h1Regular.copy(
//                color = Red50
//            )
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        state = BUTTON_STATE.DEFAULT,
        size = BUTTON_SIZE.LARGE,
        text = "Primary",
        onClick = { }
    )
}