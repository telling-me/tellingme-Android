package com.tellingus.tellingme.presentation.ui.feature.home.tellercard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.theme.Primary400

@Composable
fun MyTellerCard(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(20.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Row {
                Text(text = "나의")
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "텔러 카드")
            }
            Text(text = "꾸미기", color = Primary400)
        }
    }
}