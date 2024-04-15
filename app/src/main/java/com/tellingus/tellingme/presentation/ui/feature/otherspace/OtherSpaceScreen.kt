package com.tellingus.tellingme.presentation.ui.feature.otherspace

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.card.CommunityCard
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.navigation.OtherSpaceDestinations
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun OtherSpaceScreen(
    navController: NavController
) {
    MainLayout(
        header = { BasicAppBar() },
        content = { OtherSpaceScreenContent(navController = navController) },
        isScrollable = false,
    )
}

@Composable
fun OtherSpaceScreenContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {

        Text(text = "모두의 공간", style = TellingmeTheme.typography.head2Bold)
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "모두의 공간은 한 질문당 5일 동안만 열려요!",
            style = TellingmeTheme.typography.body2Regular
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(
                top = 24.dp,
                bottom = 24.dp
            )
        ) {
            items(items = dummyList) {
                CommunityCard(
                    id = it.id,
                    title = it.title,
                    date = it.date,
                    commentCount = it.commentCount,
                    onClickCard = { id ->
                        Log.d("로그", "CommunityCard id: $id")
                        navController.navigate("${OtherSpaceDestinations.OTHER_SPACE}/list/${id}")
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun OtherSpaceScreenPreview() {
    OtherSpaceScreen(navController = rememberNavController())
}


data class CommunityItem(
    val id: String,
    val title: String,
    val date: String,
    val commentCount: Int
)

val dummyList = listOf(
    CommunityItem(
        id = "1", title = "소속된 집단에서 내가 주로 맡는 역활은?",
        date = "오늘",
        commentCount = 1000
    ),
    CommunityItem(
        id = "2", title = "텔링미를 사용하실 때\n어떤 기분과 생각을 하시나요?",
        date = "1일 전",
        commentCount = 100
    ),
    CommunityItem(
        id = "3", title = "소속된 집단에서 내가 주로 맡는 역활은?",
        date = "2일 전",
        commentCount = 1000
    ),
    CommunityItem(
        id = "4", title = "텔링미를 사용하실 때\n어떤 기분과 생각을 하시나요?",
        date = "3일 전",
        commentCount = 100
    ),
    CommunityItem(
        id = "5", title = "소속된 집단에서 내가 주로 맡는 역활은?",
        date = "4일 전",
        commentCount = 1000
    ),
    CommunityItem(
        id = "6", title = "텔링미를 사용하실 때\n어떤 기분과 생각을 하시나요?",
        date = "5일 전",
        commentCount = 100
    )
)
