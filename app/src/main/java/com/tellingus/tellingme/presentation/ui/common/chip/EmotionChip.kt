package com.tellingus.tellingme.presentation.ui.common.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun EmotionChip(modifier: Modifier = Modifier, feeling: String = "happy") {
    Column(
        modifier = modifier
            .background(Background100, shape = RoundedCornerShape(4.dp))
            .width(63.dp)
            .height(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = getFeeling(feeling),
            style = TellingmeTheme.typography.body2Bold,
            color = Gray600
        )
    }
}

fun getFeeling(data: String): String {
    return when (data.lowercase()) {
        "happy" -> "행복해요"
        else -> "행복해요"
    }
}