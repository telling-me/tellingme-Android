package com.tellingus.tellingme.presentation.ui.common.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun IconSingleBlackButton(
    modifier: Modifier = Modifier,
    size: BUTTON_SIZE,
    text: String,
    onClick: () -> Unit,
    @DrawableRes iconResId: Int = R.drawable.icon_heart,
    enable: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides IconSingleBlackButtonRippleTheme) {
        Button(
            modifier = modifier,
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_radius)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Base0,
                contentColor = Gray600,
                disabledContainerColor = Base0,
                disabledContentColor = Gray300
            ),
            contentPadding = when(size) {
                BUTTON_SIZE.LARGE -> PaddingValues(
                    vertical = dimensionResource(R.dimen.icon_single_button_padding_vertical_large),
                    horizontal = dimensionResource(R.dimen.single_button_padding_horizontal)
                )
                BUTTON_SIZE.MEDIUM -> PaddingValues(
                    vertical = dimensionResource(R.dimen.icon_single_button_padding_vertical_medium),
                    horizontal = dimensionResource(R.dimen.single_button_padding_horizontal)
                )
                BUTTON_SIZE.SMALL -> PaddingValues(
                    vertical = dimensionResource(R.dimen.icon_single_button_padding_vertical_small),
                    horizontal = dimensionResource(R.dimen.single_button_padding_horizontal)
                )
            },
            enabled = enable
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = ""
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(
                    text = text,
                    style = when(size) {
                        BUTTON_SIZE.LARGE -> TellingmeTheme.typography.body1Bold
                        BUTTON_SIZE.MEDIUM -> TellingmeTheme.typography.body2Bold
                        BUTTON_SIZE.SMALL -> TellingmeTheme.typography.caption1Bold
                    }
                )
                Spacer(modifier = modifier.size(8.dp))
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = ""
                )
            }
        }
    }
}

private object IconSingleBlackButtonRippleTheme : RippleTheme {
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
fun IconSingleBlackButtonLargePreview() {
    Column {
        IconSingleBlackButton(
            size = BUTTON_SIZE.LARGE,
            text = "Large",
            onClick = { }
        )
        IconSingleBlackButton(
            size = BUTTON_SIZE.LARGE,
            text = "Large",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun IconSingleBlackButtonMediumPreview() {
    Column {
        IconSingleBlackButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { }
        )
        IconSingleBlackButton(
            size = BUTTON_SIZE.MEDIUM,
            text = "Medium",
            onClick = { },
            enable = false
        )
    }
}

@Preview
@Composable
fun IconSingleBlackButtonSmallPreview() {
    Column {
        IconSingleBlackButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { }
        )
        IconSingleBlackButton(
            size = BUTTON_SIZE.SMALL,
            text = "Small",
            onClick = { },
            enable = false
        )
    }
}