package com.tellingus.tellingme.presentation.ui.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.presentation.ui.feature.home.record.RecordScreen

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation(
        route = HomeDestinations.ROUTE,
        startDestination = HomeDestinations.HOME
    ) {
        composable(route = HomeDestinations.HOME) {
            HomeScreen(navController = navController)
        }

        composable(route = HomeDestinations.RECORD) {
            RecordScreen(
                navController = navController
            )
        }

    }
}