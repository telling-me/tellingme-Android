package com.tellingus.tellingme.presentation.ui.feature.mypage.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.chip.ActionChip
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.provider.useNavControllerContext

@Composable
fun AlarmScreen() {
    MainLayout(
        header = { AlarmScreenHeader() },
        content = { AlarmScreenContent() },
        isScrollable = false
    )
}

@Composable
fun AlarmScreenHeader() {
    val navController = useNavControllerContext.current

    BasicAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 5.dp, bottom = 5.dp, end = 10.dp),
        leftSlot = {
            TellingmeIconButton(iconRes = R.drawable.icon_caret_left,
                size = ButtonSize.MEDIUM,
                color = Gray500,
                onClick = { navController?.popBackStack() })
        },
    )
}

@Composable
fun AlarmScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "알림")
            ActionChip(onClick = { /*TODO*/ }, text = "전체 읽음", hasArrow = false)
        }
            LazyColumn() {
                items(items = dummyList) {it
                    AlarmCard(
                        alarmType = it.alarmType,
                        title = it.title,
                        content = it.content,
                        date = it.date
                    )
                }
            }
    }
}


@Composable
fun AlarmCard(alarmType: String, title: String, content: String = "", date: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp)
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

fun getAlarmCardTypeText(alarmType: String): String {
    val value = mapOf(
        "alarm" to "알림",
        "event" to "이벤트",
        "notice" to "공지"
    )
    return value[alarmType] ?: alarmType
}

data class AlarmItem(
    val alarmType: String,
    val title: String,
    val content: String = "",
    val date: String
)

@Preview
@Composable
fun AlarmScreenPreview() {
    AlarmScreen()
}

val dummyList = listOf(
    AlarmItem("alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.20"),
    AlarmItem(
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.09.15"
    ),
    AlarmItem("alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.13"),
    AlarmItem(
        alarmType = "notice",
        title = "서비스 오류 공지",
        content = "현재 텔링미에 에러가 발생하고 있어요. 신속하게 처리할게요! 불편을 끼쳐뜨려 죄송합니다.",
        date = "2023.09.15"
    ),
    AlarmItem(
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.24"
    ),
    AlarmItem(
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.15"
    ),
    AlarmItem("alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.20"),
    AlarmItem(
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.09.15"
    ),
    AlarmItem("alarm", title = "지난 달 서재가 완성되었어요!", date = "2023.09.13"),
    AlarmItem(
        alarmType = "notice",
        title = "서비스 오류 공지",
        content = "현재 텔링미에 에러가 발생하고 있어요. 신속하게 처리할게요! 불편을 끼쳐뜨려 죄송합니다.",
        date = "2023.09.15"
    ),
    AlarmItem(
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.24"
    ),
    AlarmItem(
        "event",
        "텔링미북 모으는 분들 주목!",
        "더 나은 텔링미 북 제작을 위한 여러분들의 의견이 궁금합니다! 설문 조사에 참여해주세요.",
        "2023.08.15"
    ),
)