package com.tellingus.tellingme.presentation.ui.common.component.card

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun CommunityCard(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    date: String,
    commentCount: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 122.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(start = 18.dp, top = 20.dp, end = 18.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = TellingmeTheme.typography.body2Bold)
            Text(text = date, style = TellingmeTheme.typography.caption1Regular)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.size(20.dp),
                    painter = painterResource(R.drawable.icon_other_space),
                    contentDescription = "",
                    tint = Gray500
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = if (commentCount < 999) commentCount.toString() else "999+",
                    style = TellingmeTheme.typography.caption1Bold,
                    color = Gray500
                )
            }

            EntranceButton(onClick = {
                Log.d("로그", "CommunityCard: $id")
            })
        }
    }
}

@Composable
fun EntranceButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .width(32.dp)
            .height(32.dp)
            .background(Primary400, shape = RoundedCornerShape(30.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = modifier.size(20.dp),
            painter = painterResource(R.drawable.icon_arrow_right),
            contentDescription = "",
            tint = Color.White
        )
    }
}