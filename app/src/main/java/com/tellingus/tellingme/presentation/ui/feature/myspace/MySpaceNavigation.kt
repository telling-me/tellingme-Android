package com.tellingus.tellingme.presentation.ui.feature.myspace

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.MySpaceDestinations

fun NavGraphBuilder.mySpaceGraph(
    navController: NavController
) {
    navigation(
        route = MySpaceDestinations.ROUTE,
        startDestination = MySpaceDestinations.MY_SPACE
    ) {
        composable(route = MySpaceDestinations.MY_SPACE) {
            MySpaceScreen(navController = navController)
        }

    }
}