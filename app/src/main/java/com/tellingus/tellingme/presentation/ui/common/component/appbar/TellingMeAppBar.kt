package com.tellingus.tellingme.presentation.ui.common.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Primary400


/**
 * AppBar 컴포즈 정의
 */

@Composable
fun BasicAppBar(
    modifier: Modifier = Modifier,
    leftSlot: @Composable (() -> Unit)? = null,
    rightSlot: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box() {
            leftSlot?.let { it() }
        }
        Box() {
            rightSlot?.let { it() }
        }
    }
}

@Preview()
@Composable
fun BasicAppBarPreview() {
    BasicAppBar(leftSlot = {
        Icon(
            painter = painterResource(R.drawable.tellingme_logo),
            contentDescription = "tellingme_logo",
            tint = Primary400
        )
    }, rightSlot = {
        Icon(
            painter = painterResource(R.drawable.icon_notice),
            contentDescription = "icon_notice",
            modifier = Modifier.size(24.dp),
            tint = Gray200
        )
    })
}