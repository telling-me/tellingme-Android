package com.tellingus.tellingme.presentation.ui.common.component.button

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun SingleBlackButton(
    modifier: Modifier = Modifier,
    size: ButtonSize,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides SingleBlackButtonRippleTheme) {
        Button(
            modifier = modifier,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Gray600,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Gray300
            ),
            contentPadding = when(size) {
                ButtonSize.LARGE -> PaddingValues(
                    vertical = dimensionResource(R.dimen.single_button_padding_vertical_large),
                    horizontal = dimensionResource(R.dimen.single_button_padding_horizontal)
                )
                ButtonSize.MEDIUM -> PaddingValues(
                    vertical = dimensionResource(R.dimen.single_button_padding_vertical_medium),
                    horizontal = dimensionResource(R.dimen.single_button_padding_horizontal)
                )
                ButtonSize.SMALL -> PaddingValues(
                    vertical = dimensionResource(R.dimen.single_button_padding_vertical_small),
                    horizontal = dimensionResource(R.dimen.single_button_padding_horizontal)
                )
            },
            enabled = enable
        ) {
            Text(
                text = text,
                style = when(size) {
                    ButtonSize.LARGE -> TellingmeTheme.typography.body1Bold
                    ButtonSize.MEDIUM -> TellingmeTheme.typography.body2Bold
                    ButtonSize.SMALL -> TellingmeTheme.typography.caption1Bold
                }
            )
        }
    }
}

private object SingleBlackButtonRippleTheme : RippleTheme {
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
fun SingleBlackButtonLargePreview() {
    Column {
        SingleBlackButton(
            size = ButtonSize.LARGE,
            text = "Large",
            onClick = { }
        )
        SingleBlackButton(
            size = ButtonSize.LARGE,
            text = "Large",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun SingleBlackButtonMediumPreview() {
    Column {
        SingleBlackButton(
            size = ButtonSize.MEDIUM,
            text = "Medium",
            onClick = { }
        )
        SingleBlackButton(
            size = ButtonSize.MEDIUM,
            text = "Medium",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun SingleBlackButtonSmallPreview() {
    Column {
        SingleBlackButton(
            size = ButtonSize.SMALL,
            text = "Small",
            onClick = { }
        )
        SingleBlackButton(
            size = ButtonSize.SMALL,
            text = "Small",
            onClick = { },
            enable = false
        )
    }
}