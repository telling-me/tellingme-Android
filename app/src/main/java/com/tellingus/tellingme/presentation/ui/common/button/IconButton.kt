package com.tellingus.tellingme.presentation.ui.common.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tellingus.tellingme.R

@Composable
fun TellingmeIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_heart),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun TellingmeIconButtonPreview() {
    TellingmeIconButton(
        onClick = {}
    )
}