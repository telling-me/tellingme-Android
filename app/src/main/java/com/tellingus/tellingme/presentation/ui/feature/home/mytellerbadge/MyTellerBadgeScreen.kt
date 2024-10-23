package com.tellingus.tellingme.presentation.ui.feature.home.mytellerbadge

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.badge.CheeseBadge
import com.tellingus.tellingme.presentation.ui.common.component.badge.TellerBadge
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun MyTellerBadgeScreen(navController: NavController) {
    MainLayout(
        isScrollable = false,
        header = { MyTellerBadgeScreenHeader(navController) },
        content = { MyTellerBadgeScreenContent() })
}

@Composable
fun MyTellerBadgeScreenContent() {
    val dummyBadgeList = listOf(
        "단골 텔러" to "또 오셨네요",
        "열정 텔러" to "많은 이야기",
        "초보 텔러" to "첫 경험",
        "소통 텔러" to "함께 이야기",
        "행복 텔러" to "웃음 가득",
        "친절 텔러" to "친절한 응대",
        "공감 텔러" to "따뜻한 공감",
        "전문 텔러" to "풍부한 지식",
        "단골 텔러" to "또 오셨네요",
        "열정 텔러" to "많은 이야기",
        "초보 텔러" to "첫 경험",
        "소통 텔러" to "함께 이야기",
        "행복 텔러" to "웃음 가득",
        "친절 텔러" to "친절한 응대",
        "공감 텔러" to "따뜻한 공감",
        "전문 텔러" to "풍부한 지식"
    )

    Column(modifier = Modifier.fillMaxHeight()) {
        Row(modifier = Modifier.padding(top = 9.dp, start = 20.dp)) {
            Text(text = "내가 받은", style = TellingmeTheme.typography.head2Regular)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "텔러 배지", style = TellingmeTheme.typography.head2Bold)
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2열 그리드
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(14.dp), // 그리드의 전체 패딩
            verticalArrangement = Arrangement.spacedBy(14.dp), // 아이템 간의 세로 간격
            horizontalArrangement = Arrangement.spacedBy(14.dp) // 아이템 간의 가로 간격
        ) {
            items(dummyBadgeList) { badge ->
                TellerBadge(
                    title = badge.first, content = badge.second
                )
            }
        }
    }
}

@Composable
fun MyTellerBadgeScreenHeader(navController: NavController) {
    BasicAppBar(modifier = Modifier
        .background(Background100)
        .height(48.dp)
        .padding(start = 20.dp, end = 20.dp)
        .fillMaxWidth(), leftSlot = {
        Icon(
            painter = painterResource(R.drawable.icon_caret_left),
            contentDescription = "tellingme_logo",
            modifier = Modifier.clickable(onClick = { navController.popBackStack() })
        )
    }, rightSlot = { CheeseBadge() })
}