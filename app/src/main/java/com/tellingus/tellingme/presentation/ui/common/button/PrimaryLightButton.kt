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
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun PrimaryLightButton(
    modifier: Modifier = Modifier,
    size: BUTTON_SIZE,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryLightButtonRippleTheme) {
        Button(
            modifier = modifier,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Gray50,
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

private object PrimaryLightButtonRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Gray50

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = Base0,
        lightTheme = true
    )
}

@Preview
@Composable
fun PrimaryLightButtonLargePreview() {
    Column {
        PrimaryLightButton(
            size = BUTTON_SIZE.LARGE,
            text = "Large",
            onClick = { }
        )
        PrimaryLightButton(
            size = BUTTON_SIZE.LARGE,
            text = "Large",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun PrimaryLightButtonMediumPreview() {
    Column {
        PrimaryLightButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { }
        )
        PrimaryLightButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun PrimaryLightButtonSmallPreview() {
    Column {
        PrimaryLightButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { }
        )
        PrimaryLightButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { },
            enable = false
        )
    }
}