package com.tellingus.tellingme.presentation.ui.common.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray400
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun ActionChip(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enable: Boolean = true,
    hasArrow: Boolean = true,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Base0,
            contentColor = Gray600,
            disabledContainerColor = Base0,
            disabledContentColor = Gray300
        ),
        border = if (enable) BorderStroke(1.dp, Gray400) else BorderStroke(1.dp, Gray200),
        enabled = enable,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 12.dp,
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                style = TellingmeTheme.typography.body2Bold
            )
            Spacer(modifier = modifier.size(2.dp))
            if (hasArrow) {
                Icon(
                    modifier = modifier.size(20.dp),
                    painter = painterResource(R.drawable.icon_caret_right),
                    contentDescription = "",
                    tint = if (enable) Gray600 else Gray300
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionChipPreview() {
    Column {
        ActionChip(onClick = { }, text = "Button", enable = true)
        ActionChip(onClick = { }, text = "Button", enable = false)
        ActionChip(onClick = { }, text = "Button", hasArrow = false)
    }
}