package com.tellingus.tellingme.presentation.ui.feature.oauth.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations

fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
    navigation(
        route = AuthDestinations.ROUTE,
        startDestination = AuthDestinations.LOGIN
    ) {
        composable(route = AuthDestinations.SPLASH) {
//            Splash
        }
        composable(route = AuthDestinations.LOGIN) {
            LoginScreen(navController)
        }
    }
}