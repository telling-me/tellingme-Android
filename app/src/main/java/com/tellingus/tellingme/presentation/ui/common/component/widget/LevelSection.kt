package com.tellingus.tellingme.presentation.ui.common.component.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.common.component.chip.PercentChip
import com.tellingus.tellingme.presentation.ui.common.component.progressbar.PercentBar
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun LevelSection(
    modifier: Modifier = Modifier,
    level: Int,
    levelDescription: String = "연속 10일만 작성하면 LV.1 달성!",
    percent: Int,
    itemColor: Color = Profile100
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Base0)
    ) {
        Column(
            modifier = modifier.padding(horizontal = 22.dp, vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LV.$level",
                    style = TellingmeTheme.typography.body1Bold,
                    color = Gray600
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(
                    text = levelDescription,
                    style = TellingmeTheme.typography.caption1Regular,
                    color = Gray500
                )
            }
            Spacer(modifier = modifier.size(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PercentBar(
                    modifier = modifier.weight(1f),
                    progress = percent/100f,
                    progressColor = itemColor
                )
                Spacer(modifier = modifier.size(11.dp))
                PercentChip(
                    percent = percent,
                    backgroundColor = itemColor
                )
            }
        }
    }
}

@Preview
@Composable
fun LevelSectionPreview() {
    LevelSection(level = 3, percent = 44)
}