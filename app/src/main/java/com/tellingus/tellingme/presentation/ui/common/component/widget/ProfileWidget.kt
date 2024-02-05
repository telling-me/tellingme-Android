package com.tellingus.tellingme.presentation.ui.common.component.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun ProfileWidget(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Profile100,
    badge: Int = R.drawable.type_badge_sample,
    nickname: String,
    description: String
) {
    Card(
        modifier = modifier.fillMaxWidth().wrapContentSize(),
        shape = RoundedCornerShape(100.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        border = BorderStroke(2.dp, Base0),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        ConstraintLayout(
            modifier = modifier.padding(
                top = 11.dp,
                bottom = 11.dp,
                start = 14.dp,
                end = 22.dp
            )
        ) {
            val (contents, shadow1, shadow2) = createRefs()

            Canvas(
                modifier = modifier.constrainAs(shadow1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            ) {
                drawLine(
                    color = Color(0x1AFFFFFF),
                    start = Offset(200.dp.toPx(), 100.dp.toPx()),
                    end = Offset(330.dp.toPx(), -100.dp.toPx()),
                    strokeWidth = 42.dp.toPx()
                )
            }
            Canvas(
                modifier = modifier.constrainAs(shadow2) {
                    start.linkTo(shadow1.start, margin = 49.dp)
                    top.linkTo(shadow1.top)
                }
            ) {
                drawLine(
                    color = Color(0x33FFFFFF),
                    start = Offset(200.dp.toPx(), 100.dp.toPx()),
                    end = Offset(330.dp.toPx(), -100.dp.toPx()),
                    strokeWidth = 42.dp.toPx()
                )
            }

            Row(
                modifier = modifier.constrainAs(contents) {
                    top.linkTo(parent.top)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.type_badge_sample),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(
                    text = nickname,
                    style = TellingmeTheme.typography.body2Bold,
                    color = Base0,
                    modifier = modifier.weight(1f)
                )
                Text(
                    text = description,
                    style = TellingmeTheme.typography.body2Bold,
                    color = Base0,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileWidgetPreview() {
    ProfileWidget(nickname = "듀이듀이", description = "연속 1일째 기록 중")
}