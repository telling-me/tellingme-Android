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
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Primary100
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.Primary50
import com.tellingus.tellingme.presentation.ui.theme.Primary500
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    size: BUTTON_SIZE,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides SecondaryButtonRippleTheme) {
        Button(
            modifier = modifier,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary50,
                contentColor = Primary400,
                disabledContainerColor = Gray50,
                disabledContentColor = Gray300
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
                    BUTTON_SIZE.LARGE -> TellingmeTheme.typography.head1Regular
                    BUTTON_SIZE.MEDIUM -> TellingmeTheme.typography.head1Regular
                    BUTTON_SIZE.SMALL -> TellingmeTheme.typography.head1Regular
                }
            )
        }
    }
}

private object SecondaryButtonRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Primary50

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = Base0,
        lightTheme = true
    )
}

@Preview
@Composable
fun SecondaryButtonLargePreview() {
    Column {
        SecondaryButton(
            size = BUTTON_SIZE.LARGE,
            text = "Large",
            onClick = { }
        )
        SecondaryButton(
            size = BUTTON_SIZE.LARGE,
            text = "Large",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun SecondaryButtonMediumPreview() {
    Column {
        SecondaryButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { }
        )
        SecondaryButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun SecondaryButtonSmallPreview() {
    Column {
        SecondaryButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { }
        )
        SecondaryButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { },
            enable = false
        )
    }
}