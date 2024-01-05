package com.tellingus.tellingme.presentation.ui.common.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.Primary500

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides FloatingButtonRippleTheme) {
        FloatingActionButton(
            modifier = modifier.size(48.dp),
            onClick = {

            },
            containerColor = Primary400,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_write),
                contentDescription = "",
                tint = Base0
            )
        }
    }
}

private object FloatingButtonRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Primary500

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = Base0,
        lightTheme = true
    )
}

@Preview(showBackground = true)
@Composable
fun FloatingButtonPreview() {
    FloatingButton {

    }
}