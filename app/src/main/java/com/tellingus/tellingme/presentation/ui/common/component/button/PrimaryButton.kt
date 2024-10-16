package com.tellingus.tellingme.presentation.ui.common.component.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Primary100
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.Primary500
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    size: ButtonSize,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true,
    isScaleDown: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.96f else 1f, label = "")

    CompositionLocalProvider(LocalRippleTheme provides PrimaryButtonRippleTheme) {
        Button(
            modifier = modifier
                .graphicsLayer(
                    scaleX = if (isScaleDown) sizeScale else 1f,
                    scaleY = if (isScaleDown) sizeScale else 1f
                ),
            interactionSource = interactionSource,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enable) Primary400 else Gray50,
                contentColor = Base0
            ),
            contentPadding = when(size) {
                ButtonSize.LARGE -> PaddingValues(
                    vertical = dimensionResource(R.dimen.button_padding_vertical_large),
                    horizontal = dimensionResource(R.dimen.button_padding_horizontal_large)
                )
                ButtonSize.MEDIUM -> PaddingValues(
                    vertical = dimensionResource(R.dimen.button_padding_vertical_medium),
                    horizontal = dimensionResource(R.dimen.button_padding_horizontal_medium)
                )
                ButtonSize.SMALL -> PaddingValues(
                    vertical = dimensionResource(R.dimen.button_padding_vertical_small),
                    horizontal = dimensionResource(R.dimen.button_padding_horizontal_small)
                )
            },
        ) {
            Text(
                text = text,
                style = when(size) {
                    ButtonSize.LARGE -> TellingmeTheme.typography.body1Bold
                    ButtonSize.MEDIUM -> TellingmeTheme.typography.body2Bold
                    ButtonSize.SMALL -> TellingmeTheme.typography.caption1Bold
                },
                color = if (enable) Base0 else Gray300
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
            size = ButtonSize.LARGE,
            text = "Primary",
            onClick = { }
        )
        PrimaryButton(
            size = ButtonSize.LARGE,
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
            size = ButtonSize.MEDIUM,
            text = "Medium",
            onClick = { }
        )
        PrimaryButton(
            size = ButtonSize.MEDIUM,
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
            size = ButtonSize.SMALL,
            text = "Small",
            onClick = { }
        )
        PrimaryButton(
            size = ButtonSize.SMALL,
            text = "Small",
            onClick = { },
            enable = false
        )
    }
}