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
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun CalendarCardView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            )
            .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 20.dp)
    ) {
        Box(
            modifier = modifier
                .background(
                    shape = RoundedCornerShape(6.dp),
                    color = Gray50
                )
                .padding(horizontal = 6.dp, vertical = 1.5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Q",
                style = TellingmeTheme.typography.body1Regular.copy(
                    color = Gray300,
                    fontSize = 14.sp
                )
            )
        }

        Text(
            modifier = modifier
                .padding(top = 10.dp),
            text = "소속된 집단에서 내가 주로 맡는 역할은?",
            style = TellingmeTheme.typography.body1Regular.copy(
                color = Gray300,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
        )

        Text(
            modifier = modifier
                .padding(top = 7.dp),
            text = "Q",
            style = TellingmeTheme.typography.body1Regular.copy(
                color = Gray600,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = modifier
                .padding(top = 10.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.emotion_angry_medium),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(4.dp))
            Box(
                modifier = modifier
                    .background(
                        shape = RoundedCornerShape(4.dp),
                        color = Gray50
                    )
                    .padding(horizontal = 6.dp, vertical = 1.5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "설레요",
                    style = TellingmeTheme.typography.body1Regular.copy(
                        color = Gray300,
                        fontSize = 14.sp
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "2023.08.26",
            style = TellingmeTheme.typography.body1Regular.copy(
                color = Gray600,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.size(8.dp))

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "첫 직장에 입사했을 때인 것 같아. 대학 다니면서는 알지 못했던 새로운 분야도 많이 알게 되고 시야도 그 시점에 많이 넓어졌어. 동료들과 어울리고 조직 문화에 적응하면서 업무에 대해 이해하는 시간을 갖느라 긴장하면서 열심히 다녔어.",
            style = TellingmeTheme.typography.body1Regular.copy(
                color = Gray600,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
fun CalendarCardViewPreview() {
    CalendarCardView()
}