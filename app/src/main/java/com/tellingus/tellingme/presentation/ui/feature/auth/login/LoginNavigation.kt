package com.tellingus.tellingme.presentation.ui.feature.auth.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations

fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
    navigation(
        route = AuthDestinations.Login.ROUTE,
        startDestination = AuthDestinations.Login.LOGIN
    ) {
        composable(route = AuthDestinations.Login.LOGIN) {
            LoginScreen(navController)
        }

    }
}