package com.tellingus.tellingme.presentation.ui.feature.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.button.BUTTON_STATE
import com.tellingus.tellingme.presentation.ui.common.card.OpinionCard
import com.tellingus.tellingme.presentation.ui.common.chip.ActionChip
import com.tellingus.tellingme.presentation.ui.common.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.section.QuestionSection
import com.tellingus.tellingme.presentation.ui.common.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.common.widget.ProfileWidget
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.presentation.viewmodel.LoginViewModel

@Composable
fun HomeScreen(
    navigateToRecordScreen: () -> Unit, navigateToOtherSpace: (name: String) -> Unit,
) {
    MainLayout(
        header = { HomeScreenHeader() },
        content = {
            HomeScreenContent(
                navigateToRecordScreen = navigateToRecordScreen,
                navigateToOtherSpace = navigateToOtherSpace
            )
        }
    )
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
    navigateToRecordScreen: () -> Unit, navigateToOtherSpace: (name: String) -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
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
                    buttonState = BUTTON_STATE.SELECTED,
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
                    loginFromKakao(
                        context = context,
                        isSuccessKakaoLogin = { token ->
                            loginViewModel.loginFromKakao(token)
                        }
                    )
                }
            )
        }
    }
}

/** 카카오디벨로퍼 공식문서에서 설명하는 발생 가능한 예외 처리 분기입니다. **/
private fun loginFromKakao(
    context: Context,
    isSuccessKakaoLogin: (String) -> Unit,
) {
    val TAG = "taag"

    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")

            isSuccessKakaoLogin(token.accessToken)
        }
    }

    // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오톡으로 로그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            } else if (token != null) {
                Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")

                isSuccessKakaoLogin(token.accessToken)
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
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
    HomeScreen(navigateToRecordScreen = { /*TODO*/ }, navigateToOtherSpace = {})
}

