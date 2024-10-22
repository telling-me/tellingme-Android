package com.tellingus.tellingme.presentation.ui.feature.home.tellercard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
fun TellerBadgeList(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(text = "내가 받은 텔러 배지", style = TellingmeTheme.typography.body1Bold)
        TellerBadge()
    }

}

@Composable
fun TellerBadge(modifier: Modifier = Modifier) {
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

        Text(text = "또 오셨네요")
        Text(text = "단골 텔러")

    }

}