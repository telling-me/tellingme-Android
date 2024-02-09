package com.tellingus.tellingme.presentation.ui.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation(
        route = HomeDestinations.ROUTE,
        startDestination = HomeDestinations.HOME
    ) {

    }
}