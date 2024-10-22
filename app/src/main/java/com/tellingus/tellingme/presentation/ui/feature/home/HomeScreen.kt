package com.tellingus.tellingme.presentation.ui.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.card.OpinionCard
import com.tellingus.tellingme.presentation.ui.common.component.chip.ActionChip
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.component.section.QuestionSection
import com.tellingus.tellingme.presentation.ui.common.component.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.common.component.widget.ProfileWidget
import com.tellingus.tellingme.presentation.ui.common.model.ButtonState
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.MyPageDestinations
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    MainLayout(
        header = {
            HomeScreenHeader(navController)
        },
        content = {
            HomeScreenContent(
                navController = navController
            )
        }
    )

    LaunchedEffect(key1 = false) {
        viewModel.getNotice()
    }

}

@Composable
fun HomeScreenHeader(navController: NavController) {
    BasicAppBar(
        modifier = Modifier
            .background(Background100)
            .height(48.dp)
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        leftSlot = {
            Icon(
                painter = painterResource(R.drawable.tellingme_logo),
                contentDescription = "tellingme_logo",
                tint = Primary400
            )
        },
        rightSlot = {
            Icon(
                painter = painterResource(R.drawable.icon_notice),
                contentDescription = "icon_notice",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = { navController.navigate(MyPageDestinations.ALARM) }),
                tint = Gray200
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    navController: NavController,
) {
    val cardList = listOf(
        "happy",
        "excited",
        "happy",
        "excited",
        "happy",
        "excited",
        "happy",
        "excited",
        "happy",
        "excited",
        "happy",
        "excited",
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            ProfileWidget(
                nickname = "듀이듀이",
                description = "연속 1일째 기록",
                modifier = Modifier.clickable { navController.navigate(HomeDestinations.TELLER_CARD) })
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
                onClickButton = {
                    navController.navigate(HomeDestinations.RECORD)
                }
            )
        }

        Column(modifier = Modifier.padding(start = 20.dp, top = 32.dp)) {
            Text(text = "나와 비슷한 텔러들의 이야기", style = TellingmeTheme.typography.body1Bold)

            val pagerState = rememberPagerState { cardList.size }

            HorizontalPager(
                modifier = Modifier.padding(top = 12.dp),
                state = pagerState,
                contentPadding = PaddingValues(end = 32.dp),
            ) { page ->
                val item = cardList[page]
                OpinionCard(
                    modifier = Modifier.padding(end = 12.dp),
                    heartCount = 1234,
                    buttonState = ButtonState.SELECTED,
                    feeling = item
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 30.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionChip(
                text = "더보기",
                onClick = {

                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenHeaderPreview() {
    HomeScreenHeader(navController = rememberNavController())
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}

