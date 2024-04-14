package com.tellingus.tellingme.presentation.ui.feature.otherspace

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.OtherSpaceDestinations
import com.tellingus.tellingme.presentation.ui.feature.otherspace.detail.OtherSpaceDetailScreen
import com.tellingus.tellingme.presentation.ui.feature.otherspace.list.OtherSpaceListScreen

fun NavGraphBuilder.otherSpaceGraph(
    navController: NavController
) {
    navigation(
        route = OtherSpaceDestinations.ROUTE,
        startDestination = OtherSpaceDestinations.OTHER_SPACE
    ) {
        composable(route = OtherSpaceDestinations.OTHER_SPACE) {
            OtherSpaceScreen(navController = navController)
        }
        composable(
            route = "${OtherSpaceDestinations.OTHER_SPACE}/list/{$KEY_ID}",
            arguments = listOf(
                navArgument(KEY_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            OtherSpaceListScreen(navController = navController)
        }
        composable(route = "${OtherSpaceDestinations.OTHER_SPACE}/detail/{$KEY_ID}",
            arguments = listOf(
                navArgument(KEY_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            OtherSpaceDetailScreen(navController = navController)
        }
    }
}

const val KEY_ID = "key-id"
