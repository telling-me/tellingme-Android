package com.tellingus.tellingme.presentation.ui.common.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun EmptyCalendarCardView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            )
            .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(6.dp),
                    color = Gray50
                )
                .padding(horizontal = 6.dp, vertical = 1.5.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = modifier
                        .size(120.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.emotion_angry_medium),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = "이 날 작성한 답변이 없어요!",
                    style = TellingmeTheme.typography.body2Regular.copy(
                        color = Gray500,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}
