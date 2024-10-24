package com.tellingus.tellingme.presentation.ui.common.component.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.tellingus.tellingme.presentation.ui.theme.Gray50
import com.tellingus.tellingme.presentation.ui.theme.Profile100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    response: ProfileCardResponse = ProfileCardResponse(),
    backgroundColor: Color = Profile100
) {
    Card(
        modifier = modifier.fillMaxWidth().wrapContentSize(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        border = BorderStroke(2.dp, Base0),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        ConstraintLayout(
            modifier = modifier.padding(
                top = 8.dp,
                bottom = 16.dp,
                start = 22.dp,
                end = 22.dp
            )
        ) {
            val (topBox, badge, nickname, levelBox, dayBox, shadow1, shadow2) = createRefs()

            Canvas(
                modifier = modifier.constrainAs(shadow1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            ) {
                drawLine(
                    color = Color(0x1AFFFFFF),
                    start = Offset(30.dp.toPx(), 220.dp.toPx()),
                    end = Offset(420.dp.toPx(), 40.dp.toPx()),
                    strokeWidth = 55.dp.toPx()
                )
            }
            Canvas(
                modifier = modifier.constrainAs(shadow2) {
                    start.linkTo(shadow1.start)
                    // top마진을 55.dp로 설정하면 dp <-> px 단위때문에 조금 겹치는데 확인 필요.
                    // 일단 6.5로 하니까 딱 맞긴 함. (10% 만큼 차이인건지..?)
                    top.linkTo(shadow1.top, margin = 60.5.dp)
                }
            ) {
                drawLine(
                    color = Color(0x33FFFFFF),
                    start = Offset(30.dp.toPx(), 220.dp.toPx()),
                    end = Offset(420.dp.toPx(), 40.dp.toPx()),
                    strokeWidth = 55.dp.toPx()
                )
            }

            Row(
                modifier = modifier.constrainAs(topBox) {
                    top.linkTo(parent.top)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "나도 날 잘 몰라요, 슈퍼 루키(예시)",
                    style = TellingmeTheme.typography.body1Bold,
                    color = Base0
                )
                Spacer(modifier = modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.icon_level_sample),
                    contentDescription = "",
                    modifier = modifier.size(40.dp)
                )
            }

            Image(
                painter = painterResource(R.drawable.type_badge_sample),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .constrainAs(badge) {
                        top.linkTo(topBox.bottom, margin = 6.dp)
                        centerHorizontallyTo(parent)
                    }
            )

            Text(
                text = "듀이듀이",
                style = TellingmeTheme.typography.body1Bold,
                color = Base0,
                modifier = modifier.constrainAs(nickname) {
                    top.linkTo(badge.bottom, margin = 15.dp)
                    centerHorizontallyTo(parent)
                }
            )

            Column(
                modifier = modifier.constrainAs(levelBox) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Text(
                    text = "레벨",
                    style = TellingmeTheme.typography.caption1Bold,
                    color = Gray50
                )
                Text(
                    text = "LV.1",
                    style = TellingmeTheme.typography.caption1Bold,
                    color = Base0
                )
            }

            Column(
                modifier = modifier.constrainAs(dayBox) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Text(
                    text = "연속 작성일",
                    style = TellingmeTheme.typography.caption1Bold,
                    color = Gray50
                )
                Text(
                    text = "1일째",
                    style = TellingmeTheme.typography.caption1Bold,
                    color = Base0
                )
            }
        }
    }
}

// ProfileCard를 구성하는 데이터는 아마 json으로 한 번에 내려받지 않을까 싶습니다.
data class ProfileCardResponse(
    // 닉네임, 레벨, 뱃지, 프로필 아이콘 등등 ...
    val nickname: String = "",
    val description : String = "", // 설명
    val level: String = "", // 레벨
    val consecutiveWritingDate : String = "", // 연속작성일
    val profileIcon : String = ""
)

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ProfileCard()
}