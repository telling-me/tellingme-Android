package com.tellingus.tellingme.presentation.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tellingus.tellingme.presentation.ui.common.appbar.TellingMeAppBar
import com.tellingus.tellingme.presentation.ui.common.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.section.QuestionSection
import com.tellingus.tellingme.presentation.ui.common.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.common.widget.ProfileWidget
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun HomeScreen(
    navigateToRecordScreen: () -> Unit,
    navigateToOtherSpace: (name: String) -> Unit
) {
    MainLayout(
        header = { TellingMeAppBar(title = "헤더영역") },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background100)
            ) {
                Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                    ProfileWidget(nickname = "듀이듀이", description = "연속 1일째 기록")
                }
                Box(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp)) {
                    LevelSection(level = 1, percent = 50)
                }
                Column(modifier = Modifier.padding(top = 32.dp, start = 20.dp, end = 20.dp)) {
                    Text(text = "오늘의 질문", style = TellingmeTheme.typography.body1Bold)
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = "12명이 대답했어요!",
                        style = TellingmeTheme.typography.caption1Regular
                    )
                    QuestionSection(
                        modifier = Modifier.padding(top = 12.dp),
                        title = "지금까지의 나의 인생을 두 단계로\n나눈다면 어느 시점에 구분선을 둘 건가요?",
                        description = "그 역활이 나의 성향을 반영할 수 있어요",
                        onClickButton = navigateToRecordScreen
                    )
                }

                Column(modifier = Modifier.padding(start = 20.dp, top = 32.dp)) {
                    Text(text = "나와 비슷한 텔러들의 이야기", style = TellingmeTheme.typography.body1Bold)
                    
                    Column {
                        Text(text = "의견 카드 캐로셀")
                    }
                    
                }
            }
        })
}


