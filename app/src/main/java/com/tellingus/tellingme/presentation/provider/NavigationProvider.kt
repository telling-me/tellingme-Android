package com.tellingus.tellingme.presentation.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val AmbientNavController = staticCompositionLocalOf<NavController?> { null }
@Composable
fun NavigationProvider(
    navController: NavController,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(AmbientNavController provides navController) {
        content()
    }
}