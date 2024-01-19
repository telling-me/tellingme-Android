package com.tellingus.tellingme.presentation.ui.common.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

enum class TOOLTIP_TYPE {
    BASIC, HELP
}

@Composable
fun ToolTip(
    modifier: Modifier = Modifier,
    type: TOOLTIP_TYPE,
    text: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                when (type) {
                    TOOLTIP_TYPE.BASIC -> Profile100
                    TOOLTIP_TYPE.HELP -> Gray500
                }
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TellingmeTheme.typography.caption1Bold,
            color = Base0
        )
    }
}

@Preview
@Composable
fun ToolTipPreview() {
    Column {
        ToolTip(
            type = TOOLTIP_TYPE.BASIC,
            text = "감정을 선택해주세요!"
        )
        ToolTip(
            type = TOOLTIP_TYPE.HELP,
            text = "감정을 선택해주세요!"
        )
    }
}