package com.tellingus.tellingme.presentation.ui.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.feature.auth.login.loginGraph
import com.tellingus.tellingme.presentation.ui.feature.auth.signup.signupGraph

fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    navigation(
        route = AuthDestinations.ROUTE,
        startDestination = AuthDestinations.Login.ROUTE
    ) {
        composable(route = AuthDestinations.SPLASH) {
//            Splash
        }

        loginGraph(
            navController = navController
        )

        signupGraph(
            navController = navController
        )
    }
}