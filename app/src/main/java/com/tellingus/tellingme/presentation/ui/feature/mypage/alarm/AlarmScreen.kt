package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.chip.ActionChip
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary100
import com.tellingus.tellingme.presentation.ui.theme.Red600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun AlarmScreen(
    navController: NavController
) {
    MainLayout(
        header = { AlarmScreenHeader { navController.popBackStack() } },
        content = { AlarmScreenContent() },
        isScrollable = false,
        background = Color.White
    )
}

@Composable
fun AlarmScreenHeader(navigateToPreviousScreen: () -> Unit) {

    BasicAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 5.dp, bottom = 5.dp, end = 10.dp),
        leftSlot = {
            TellingmeIconButton(iconRes = R.drawable.icon_caret_left,
                size = ButtonSize.MEDIUM,
                color = Gray500,
                onClick = { navigateToPreviousScreen() })
        },
    )
}

@Composable
fun AlarmScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "알림")
            ActionChip(onClick = { /*TODO*/ }, text = "전체 읽음", hasArrow = false)
        }
        LazyColumn() {
            items(items = dummyList) {
                it
                AlarmCard(
                    alarmType = it.alarmType,
                    title = it.title,
                    content = it.content,
                    date = it.date,
                    isRead = it.isRead
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun AlarmCard(
    alarmType: String,
    title: String,
    content: String = "",
    date: String,
    isRead: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val swipeableState = rememberSwipeableState(initialValue = 0)
    val coroutineScope = rememberCoroutineScope()
    val squareSize = 80.dp
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.White)
            .swipeable(
                state = swipeableState,
                orientation = Orientation.Horizontal,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                velocityThreshold = 1000.dp
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            TextButton(
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .background(Red600),
                onClick = {
                    coroutineScope.launch {
                        swipeableState.animateTo(0, tween(600, 0))
                    }
                }) {
                Text(text = "삭제", color = Color.White)
            }
        }


        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        swipeableState.offset.value.roundToInt(),
                        0
                    )
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)
                    .alpha(if (isRead === true || isPressed === true) 0.5f else 1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {}
                    )
            ) {
                Text(
                    text = getAlarmCardTypeText(alarmType),
                    color = Primary100,
                    style = TellingmeTheme.typography.caption1Bold,
                )
                Text(
                    text = title,
                    color = Gray600,
                    style = TellingmeTheme.typography.body2Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
                if (content !== "") {
                    Text(
                        text = content,
                        color = Gray600,
                        style = TellingmeTheme.typography.body2Regular,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Text(
                    text = date,
                    color = Gray600,
                    style = TellingmeTheme.typography.caption1Regular,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }

}

fun getAlarmCardTypeText(alarmType: String): String {
    val value = mapOf(
        "alarm" to "알림",
        "event" to "이벤트",
        "notice" to "공지"
    )
    return value[alarmType] ?: alarmType
}

data class AlarmItem(
    val id: Int,
    val alarmType: String,
    val title: String,
    val content: String = "",
    val date: String,
    val isRead: Boolean = false,
)

@Preview
@Composable
fun AlarmScreenPreview() {
    AlarmScreen(navController = rememberNavController())
}

val dummyList = listOf(
    AlarmItem(id = 1, "alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.20", isRead = true),
    AlarmItem(
        id = 2,
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.09.15"
    ),
    AlarmItem(id = 3, "alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.13", isRead = true),
    AlarmItem(
        id = 4,
        alarmType = "notice",
        title = "서비스 오류 공지",
        content = "현재 텔링미에 에러가 발생하고 있어요. 신속하게 처리할게요! 불편을 끼쳐뜨려 죄송합니다.",
        date = "2023.09.15"
    ),
    AlarmItem(
        id = 5,
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.24"
    ),
    AlarmItem(
        id = 6,
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.15"
    ),
    AlarmItem(
        id = 7,
        "alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.20"
    ),
    AlarmItem(
        id = 8,
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.09.15"
    ),
    AlarmItem(
        id = 9,
        "alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.13"
    ),
    AlarmItem(
        id = 10,
        alarmType = "notice",
        title = "서비스 오류 공지",
        content = "현재 텔링미에 에러가 발생하고 있어요. 신속하게 처리할게요! 불편을 끼쳐뜨려 죄송합니다.",
        date = "2023.09.15"
    ),
    AlarmItem(
        id = 11,
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.24"
    ),
    AlarmItem(
        id = 12,
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.15"
    ),
)