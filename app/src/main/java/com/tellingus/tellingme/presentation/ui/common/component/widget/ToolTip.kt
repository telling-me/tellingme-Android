package com.tellingus.tellingme.presentation.ui.common.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.common.model.ToolTipType
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun ToolTip(
    modifier: Modifier = Modifier,
    type: ToolTipType,
    text: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(
                when (type) {
                    ToolTipType.BASIC -> Profile100
                    ToolTipType.HELP -> Gray500
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
            type = ToolTipType.BASIC,
            text = "감정을 선택해주세요!"
        )
        ToolTip(
            type = ToolTipType.HELP,
            text = "감정을 선택해주세요!"
        )
    }
}