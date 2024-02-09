package com.tellingus.tellingme.presentation.ui.feature.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.tellingus.tellingme.presentation.ui.feature.login.LoginContract
import com.tellingus.tellingme.presentation.ui.feature.login.LoginViewModel
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.util.TAG
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navigateToOtherSpace: (name: String) -> Unit,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    MainLayout(
        header = { HomeScreenHeader() },
        content = {
            HomeScreenContent(
//                navigateToRecordScreen = navigateToRecordScreen,
                navigateToRecordScreen = {
                    navController.navigate(HomeDestinations.RECORD)
                },
                navigateToOtherSpace = navigateToOtherSpace,
                viewModel = viewModel
            )
        }
    )

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.MoveToOauthJoin -> {
                    Log.d(TAG, "111")
                }
                is LoginContract.Effect.MoveToHome -> {
                    Log.d(TAG, "222")
                }
            }
        }
    }
}

@Composable
fun HomeScreenHeader() {
    BasicAppBar(
        modifier = Modifier
            .background(Color.White)
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
                modifier = Modifier.size(24.dp),
                tint = Gray200
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    navigateToRecordScreen: () -> Unit,
    navigateToOtherSpace: (name: String) -> Unit,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
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

            HorizontalPager(
                modifier = Modifier.padding(top = 12.dp),
                pageCount = cardList.size,
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
                    viewModel.processEvent(LoginContract.Event.KakaoLoginButtonClicked(context))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenHeaderPreview() {
    HomeScreenHeader()
}

@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen(navigateToRecordScreen = { /*TODO*/ }, navigateToOtherSpace = {})
}

