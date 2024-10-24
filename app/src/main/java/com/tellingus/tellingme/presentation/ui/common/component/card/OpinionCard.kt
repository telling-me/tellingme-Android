package com.tellingus.tellingme.presentation.ui.common.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.button.HeartButton
import com.tellingus.tellingme.presentation.ui.common.component.chip.EmotionChip
import com.tellingus.tellingme.presentation.ui.common.model.ButtonState
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun OpinionCard(
    modifier: Modifier = Modifier,
    heartCount: Int,
    buttonState: ButtonState,
    feeling: String,
    description: String = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다.",
    onClick: () -> Unit = {},
    type: String = "default"
) {

    if (type === "default") {
        DefaultCard(modifier, heartCount, buttonState, feeling, description, onClick)
    } else if (type === "full") {
        FullCard(modifier, heartCount, buttonState, feeling, description, onClick)
    }
}

@Composable
fun DefaultCard(
    modifier: Modifier = Modifier,
    heartCount: Int,
    buttonState: ButtonState,
    feeling: String,
    description: String = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다.",
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 130.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .padding(start = 22.dp, end = 22.dp, top = 20.dp, bottom = 20.dp)
    ) {
        Row(modifier = Modifier.clickable {
            if (onClick != {}) {
                onClick()
            }
        }) {
            Column(
                modifier = Modifier
                    .height(98.dp)
                    .padding(end = 22.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.emotion_happy_medium),
                    contentDescription = "",
                    modifier = Modifier.size(52.dp),
                )
            }
            Column() {
                EmotionChip(feeling = feeling)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = description,
                    style = TellingmeTheme.typography.caption1Regular
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = (-12).dp)
        ) {
            HeartButton(heartCount = heartCount, buttonState = buttonState, onClick = {})
        }
    }
}

@Composable
fun FullCard(
    modifier: Modifier = Modifier,
    heartCount: Int,
    buttonState: ButtonState,
    feeling: String,
    description: String = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다.",
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.padding(top = 22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.emotion_happy_medium),
                    contentDescription = "",
                    modifier = Modifier.size(52.dp),
                )
                EmotionChip(modifier = Modifier.padding(top = 9.5.dp), feeling = feeling)

                Column(
                    modifier = Modifier.padding(
                        top = 24.dp,
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                ) {
                    Text(text = "첫 직장에 입사했을 때인 것 같아. 대학 다니면서는 알지 못했던 새로운 분야도 많이 알게 되고 시야도 그 시점에 많이 넓어졌어. 동료들과 어울리고 조직 문화에 적응하면서 업무에 대해 이해하는 시간을 갖느라 긴장하면서 열심히 다녔어. 그시기가 내 인생의 터닝 포인트라 생각해. 이 전엔 배움의 시기였다면 그 때 이후로 성장과 실전의 시기로 들어섰단 느낌이 들었거든. 첫 직장에 입사했을 때인 것 같아. 대학 다니면서는 알지 못했던 새로운 분야도 많이 알게 되고 시야도 그 시점에 많이 넓어졌어. 동료들과 어울리고 조직 문화에 적응하면서 업무에 대해 이해하는 시간을 갖느라 긴장하면서 열심히 다녔어. 그시기가 내 인생의 터닝 포인트라 생각해. 이 전엔 배움의 시기였다면 그 때 이후로 성장과 실전의 시기로 들어섰단 느낌이 들었거든. 대학 다니면서는 알지 못했던 새로운 분야도 많이 알게 되고 시야도 그 시점에 많이 넓어졌어. 동료들과 어울리고 조직 문화에 적응하면서 업무에 대해 이해하는 시간을 갖느라 긴장하면서 열심히 다녔어. 그시기가 내 인생의 터닝 포인트라 생각해. 이 전엔 배움의 시기였다면 그 때 이후로 성장과 실전의 시기로 들어섰단 느낌이 들었거든. 그시기가 내 인생의 터닝 포인트라 생각해. 이 전엔 배움의 시기였다면 그 때 이후로 성장과 실전의 시기로 들어섰단 느낌이 들었거든. 하하하")
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 10.dp)
        ) {
            HeartButton(heartCount = heartCount, buttonState = buttonState, onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OpinionCardPreview() {
    Column {
        OpinionCard(
            heartCount = 1234,
            buttonState = ButtonState.SELECTED,
            feeling = "happy",
            type = "default"
        )
    }
}