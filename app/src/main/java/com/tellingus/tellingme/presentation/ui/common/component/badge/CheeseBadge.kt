package com.tellingus.tellingme.presentation.ui.common.component.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Background200
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun CheeseBadge(modifier: Modifier = Modifier) {


    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Background200)
            .height(38.dp)
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.icon_round_cheese),
            contentDescription = "icon_notice",
            modifier = Modifier.size(26.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "111개", color = Gray600, style = TellingmeTheme.typography.body2Bold)
    }
}