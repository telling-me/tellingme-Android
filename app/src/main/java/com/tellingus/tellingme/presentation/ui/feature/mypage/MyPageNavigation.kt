package com.tellingus.tellingme.presentation.ui.feature.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.MyPageDestinations
import com.tellingus.tellingme.presentation.ui.feature.mypage.alarm.AlarmScreen

fun NavGraphBuilder.myPageGraph(
    navController: NavController
) {
    navigation(
        route = MyPageDestinations.ROUTE,
        startDestination = MyPageDestinations.MY_PAGE
    ) {
        composable(route = MyPageDestinations.MY_PAGE) {
            MyPageScreen(navController = navController)
        }
        composable(route = MyPageDestinations.ALARM) {
            AlarmScreen(navController)
        }
    }
}