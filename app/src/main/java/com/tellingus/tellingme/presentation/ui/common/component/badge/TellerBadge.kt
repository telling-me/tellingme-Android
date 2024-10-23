package com.tellingus.tellingme.presentation.ui.common.component.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun TellerBadge(modifier: Modifier = Modifier, title: String = "", content: String = "") {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .width(149.dp)
            .height(167.dp)
            .background(Base0),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(R.drawable.emotion_happy_medium),
            contentDescription = "",
            modifier = Modifier.size(52.dp),
        )
        Text(text = "${title}", style = TellingmeTheme.typography.caption1Regular)
        Text(text = "${content}", style = TellingmeTheme.typography.body2Bold)
    }
}