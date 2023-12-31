package com.tellingus.tellingme.presentation.ui.common.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Primary100
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.Primary500
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    size: BUTTON_SIZE,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryButtonRippleTheme) {
        Button(
            modifier = modifier,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary400,
                contentColor = Base0,
                disabledContainerColor = Primary100,
                disabledContentColor = Base0
            ),
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
            },
            enabled = enable
        ) {
            Text(
                text = text,
                style = when(size) {
                    BUTTON_SIZE.LARGE -> TellingmeTheme.typography.body1Bold
                    BUTTON_SIZE.MEDIUM -> TellingmeTheme.typography.body2Bold
                    BUTTON_SIZE.SMALL -> TellingmeTheme.typography.caption1Bold
                }
            )
        }
    }
}

private object PrimaryButtonRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Primary500

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = Base0,
        lightTheme = true
    )
}

@Preview
@Composable
fun PrimaryButtonLargePreview() {
    Column {
        PrimaryButton(
            size = BUTTON_SIZE.LARGE,
            text = "Primary",
            onClick = { }
        )
        PrimaryButton(
            size = BUTTON_SIZE.LARGE,
            text = "Primary",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun PrimaryButtonMediumPreview() {
    Column {
        PrimaryButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { }
        )
        PrimaryButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun PrimaryButtonSmallPreview() {
    Column {
        PrimaryButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { }
        )
        PrimaryButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { },
            enable = false
        )
    }
}