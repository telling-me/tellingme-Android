package com.tellingus.tellingme.presentation.ui.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.presentation.ui.feature.home.mytellerbadge.MyTellerBadgeScreen
import com.tellingus.tellingme.presentation.ui.feature.home.record.RecordScreen
import com.tellingus.tellingme.presentation.ui.feature.home.tellercard.TellerCardScreen
import com.tellingus.tellingme.presentation.ui.feature.home.tellercardtuning.TellerCardTuningScreen

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
        composable(route = HomeDestinations.TELLER_CARD) {
            TellerCardScreen(navController = navController)
        }
        composable(route = HomeDestinations.MY_TELLER_BADGE) {
            MyTellerBadgeScreen(navController = navController)
        }
        composable(route = HomeDestinations.TELLER_CARD_TUNING) {
            TellerCardTuningScreen(navController = navController)
        }

    }
}