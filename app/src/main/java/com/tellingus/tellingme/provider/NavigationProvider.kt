package com.tellingus.tellingme.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val useNavControllerContext = staticCompositionLocalOf<NavController?> { null }

@Composable
fun NavigationProvider(
    navController: NavController,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(useNavControllerContext provides navController) {
        content()
    }
}