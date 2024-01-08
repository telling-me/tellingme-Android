package com.tellingus.tellingme.presentation.ui.feature.home

import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tellingus.tellingme.presentation.ui.common.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.widget.LevelSection
import com.tellingus.tellingme.presentation.ui.common.widget.ProfileWidget
import com.tellingus.tellingme.presentation.ui.theme.Background100
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme

@Composable
fun HomeScreen(
    navigateToOtherSpace: (name: String) -> Unit
) {
    MainLayout(content = {
        Column(
            modifier = Modifier
                .padding(top = 56.dp)
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
            }
        }
    })
}