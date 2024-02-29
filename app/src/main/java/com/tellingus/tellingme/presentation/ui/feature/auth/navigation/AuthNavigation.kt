package com.tellingus.tellingme.presentation.ui.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.feature.auth.login.LoginScreen
import com.tellingus.tellingme.presentation.ui.feature.auth.signup.SignupNickNameScreen

fun NavGraphBuilder.authGraph(
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

        composable(
            route = "${AuthDestinations.SIGNUP}/{socialId}",
            arguments = listOf(navArgument("socialId") {
                type = NavType.StringType
                defaultValue = "-1"
            })
        ) {
            SignupNickNameScreen(
                navController = navController,
                socialId = it.arguments?.getString("socialId")!!
            )
        }
    }
}