package com.tellingus.tellingme.presentation.ui.common.tabBar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.provider.NavigationProvider
import com.tellingus.tellingme.presentation.ui.screen.everyspace.EverySpaceScreen
import com.tellingus.tellingme.presentation.ui.screen.home.HomeScreen
import com.tellingus.tellingme.presentation.ui.screen.my.MyScreen
import com.tellingus.tellingme.presentation.ui.screen.myspace.MySpaceScreen
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBar() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.MySpace,
        Screen.EverySpace,
        Screen.My,
    )

    Scaffold(bottomBar = {
        BottomNavigation(backgroundColor = Color.White) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            items.forEach { screen ->
                val isSelect = currentDestination === screen.route

                BottomNavigationItem(icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        tint = if (isSelect) Gray500 else Gray200,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                    label = {
                        Text(
                            text = screen.title,
                            fontSize = 10.sp,
                            color = if (isSelect) Gray600 else Gray300
                        )
                    },
                    selected = currentDestination === screen.route,
                    selectedContentColor = Primary400,
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    })
            }
        }
    }) { innerPadding ->
        // NavHost 컴포즈를 따로 분리해서 사용하는게 좋을지 고민..
        NavigationProvider(navController = navController) {
            NavHost(
                navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) { HomeScreen(navController) }
                composable(Screen.MySpace.route) { MySpaceScreen(navController) }
                composable(Screen.EverySpace.route) { EverySpaceScreen(navController) }
                composable(Screen.My.route) { MyScreen(navController) }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: Int) {
    object Home : Screen("home", "홈", R.drawable.icon_home)
    object MySpace : Screen("myspace", "나의 공간", R.drawable.icon_my_book_shelf)
    object EverySpace : Screen("everySpace", "모두의 공간", R.drawable.icon_other_space)
    object My : Screen("my", "MY", R.drawable.icon_user)
}