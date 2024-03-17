package com.tellingus.tellingme.presentation.ui.feature.otherspace.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.FloatingButton
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.card.OpinionCard
import com.tellingus.tellingme.presentation.ui.common.component.chip.ChoiceChip
import com.tellingus.tellingme.presentation.ui.common.component.layout.OtherSpaceLayout
import com.tellingus.tellingme.presentation.ui.common.component.section.QuestionSection
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.model.ButtonState
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Gray500

@Composable
fun OtherSpaceDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    OtherSpaceLayout(
        header = {
            BasicAppBar(modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 12.dp), leftSlot = {
                TellingmeIconButton(iconRes = R.drawable.icon_caret_left,
                    size = ButtonSize.MEDIUM,
                    color = Gray500,
                    onClick = {
                        navController.popBackStack()
                    })
            })
        },
        content = { OtherSpaceDetailScreenContent(navController = navController) },
        isScrollable = false,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OtherSpaceDetailScreenContent(navController: NavController) {
    var isSelected by remember { mutableStateOf("recently") }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 0.dp)
            .fillMaxHeight()
    ) {
        FloatingButton(
            modifier = Modifier
                .offset(screenWidth - 90.dp, screenHeight - 90.dp)
                .zIndex(1f)
        ) {}

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(
                top = 0.dp, bottom = 24.dp
            )
        ) {
            item {
                QuestionSection(
                    title = "지금까지의 나의 인생을 두 단계로\n" + "나눈다면 어느 시점에 구분선을 둘 건가요?",
                    description = "스스로 크게 변화한 시점을 떠올려봐요.",
                    isButtonVisible = false,
                    bgColor = Background100
                )
            }
            stickyHeader {
                Row(modifier = Modifier.padding(top = 0.dp, bottom = 16.dp)) {
                    ChoiceChip(selected = isSelected == "recently", text = "최신순", onClick = {
                        isSelected = "recently"
                    })

                    Spacer(modifier = Modifier.width(8.dp))

                    ChoiceChip(selected = isSelected == "related", text = "관련순", onClick = {
                        isSelected = "related"
                    })

                    Spacer(modifier = Modifier.width(8.dp))

                    ChoiceChip(selected = isSelected == "sympathy", text = "공감순", onClick = {
                        isSelected = "sympathy"
                    })
                }
            }
            items(items = mockData) {
                OpinionCard(
                    heartCount = it.heartCount,
                    buttonState = it.buttonState,
                    feeling = it.feeling,
                    description = it.description
                )
            }
        }
    }
}


@Preview
@Composable
fun OtherSpaceDetailScreenPreview() {
    OtherSpaceDetailScreen(navController = rememberNavController())
}

data class OpinionItem(
    val heartCount: Int, val buttonState: ButtonState, val feeling: String, val description: String
)

val mockData = listOf(
    OpinionItem(
        heartCount = 999,
        buttonState = ButtonState.ENABLED,
        feeling = "excited",
        description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
    ), OpinionItem(
        heartCount = 100,
        buttonState = ButtonState.SELECTED,
        feeling = "happy",
        description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
    ), OpinionItem(
        heartCount = 14,
        buttonState = ButtonState.ENABLED,
        feeling = "excited",
        description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
    ), OpinionItem(
        heartCount = 15,
        buttonState = ButtonState.DISABLED,
        feeling = "happy",
        description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
    ), OpinionItem(
        heartCount = 11,
        buttonState = ButtonState.ENABLED,
        feeling = "excited",
        description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
    ), OpinionItem(
        heartCount = 87,
        buttonState = ButtonState.DISABLED,
        feeling = "happy",
        description = "나는 보통 집단 안에서 이야기 나온 내용에서 핵심을 뽑아내 정리하는 것을 잘하는 것 같다. 예를 들면 학교 팀플을 진행할 때 빛을 보인다. 팀원들의 의견을 수용하여 핵심만을 요약한다."
    )
)