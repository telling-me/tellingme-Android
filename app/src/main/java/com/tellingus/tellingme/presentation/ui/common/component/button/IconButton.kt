package com.tellingus.tellingme.presentation.ui.common.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.model.IconButtonState
import com.tellingus.tellingme.presentation.ui.theme.Gray100
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.Red400

@Composable
fun TellingmeIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    state: IconButtonState = IconButtonState.DEFAULT,
    size: ButtonSize,
    color: Color,
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = "",
        modifier = modifier
            .size(
                when (size) {
                    ButtonSize.LARGE -> dimensionResource(R.dimen.icon_size_large)
                    ButtonSize.MEDIUM -> dimensionResource(R.dimen.icon_size_medium)
                    ButtonSize.SMALL -> dimensionResource(R.dimen.icon_size_small)
                }
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
                enabled = true
            ),
        tint = when(state) {
            IconButtonState.DEFAULT, IconButtonState.SELECTED -> color
            IconButtonState.ENABLED -> Gray200
            IconButtonState.DISABLED -> Gray100
        }
    )
}

@Preview
@Composable
fun TellingmeIconButtonPreview() {
    Column {
        TellingmeIconButton(iconRes = R.drawable.icon_heart, size = ButtonSize.LARGE, color = Gray500) {}
        TellingmeIconButton(iconRes = R.drawable.icon_level_sample, size = ButtonSize.MEDIUM, color = Profile100) {}
        TellingmeIconButton(iconRes = R.drawable.icon_home, size = ButtonSize.SMALL, color = Red400) {}
    }
}