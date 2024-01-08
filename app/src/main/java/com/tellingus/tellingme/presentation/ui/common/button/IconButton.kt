package com.tellingus.tellingme.presentation.ui.common.button

import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray100
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.Red400

@Composable
fun TellingmeIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    state: ICON_BUTTON_STATE = ICON_BUTTON_STATE.DEFAULT,
    size: BUTTON_SIZE,
    color: Color,
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(iconRes),
        contentDescription = "",
        modifier = modifier
            .size(
                when (size) {
                    BUTTON_SIZE.LARGE -> dimensionResource(R.dimen.icon_size_large)
                    BUTTON_SIZE.MEDIUM -> dimensionResource(R.dimen.icon_size_medium)
                    BUTTON_SIZE.SMALL -> dimensionResource(R.dimen.icon_size_small)
                }
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
        tint = when(state) {
            ICON_BUTTON_STATE.DEFAULT, ICON_BUTTON_STATE.SELECTED -> color
            ICON_BUTTON_STATE.ENABLED -> Gray200
            ICON_BUTTON_STATE.DISABLED -> Gray100
        }
    )
}

@Preview
@Composable
fun TellingmeIconButtonPreview() {
    Column {
        TellingmeIconButton(iconRes = R.drawable.icon_heart, size = BUTTON_SIZE.LARGE, color = Gray500) {}
        TellingmeIconButton(iconRes = R.drawable.icon_level_sample, size = BUTTON_SIZE.MEDIUM, color = Profile100) {}
        TellingmeIconButton(iconRes = R.drawable.icon_home, size = BUTTON_SIZE.SMALL, color = Red400) {}
    }
}