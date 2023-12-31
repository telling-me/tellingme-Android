package com.tellingus.tellingme.presentation.ui.common.chip

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun PercentChip(
    modifier: Modifier = Modifier,
    percent: Int,
    backgroundColor: Color = Profile100
) {
    Card(
        shape = RoundedCornerShape(100.dp),
        colors = CardDefaults.cardColors(backgroundColor),

    ) {
        Text(
            modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = "${percent}%",
            style = TellingmeTheme.typography.caption1Bold,
            color = Base0
        )
    }
}

@Preview
@Composable
fun PercentChipPreview() {
    PercentChip(percent = 12)
}