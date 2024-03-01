package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations

fun NavGraphBuilder.signupGraph(
    navController: NavController
) {
    navigation(
        route = AuthDestinations.Signup.ROUTE,
        startDestination = AuthDestinations.Signup.SIGNUP_NICKNAME
    ) {
        composable(
            route = "${AuthDestinations.Signup.SIGNUP_NICKNAME}/{socialId}",
            arguments = listOf(navArgument("socialId") {
                type = NavType.StringType
                defaultValue = "-1"
            })
        ) {
            SignupNicknameScreen(
                navController = navController,
                socialId = it.arguments?.getString("socialId")!!
            )
        }

        composable(route = AuthDestinations.Signup.SIGNUP_BIRTH_GENDER) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry("${AuthDestinations.Signup.SIGNUP_NICKNAME}/{socialId}")
            }
            SignupBirthGenderScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(route = AuthDestinations.Signup.SIGNUP_JOB) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry("${AuthDestinations.Signup.SIGNUP_NICKNAME}/{socialId}")
            }
            SignupJobScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(route = AuthDestinations.Signup.SIGNUP_WORRY) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry("${AuthDestinations.Signup.SIGNUP_NICKNAME}/{socialId}")
            }
            SignupWorryScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }
    }
}