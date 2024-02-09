package com.tellingus.tellingme.presentation.ui.feature

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.BottomNavigationItem
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.MyPageDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.MySpaceDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.OtherSpaceDestinations
import com.tellingus.tellingme.presentation.ui.feature.home.HomeScreen
import com.tellingus.tellingme.presentation.ui.feature.home.record.RecordScreen
import com.tellingus.tellingme.presentation.ui.feature.mypage.MyPageScreen
import com.tellingus.tellingme.presentation.ui.feature.mypage.alarm.AlarmScreen
import com.tellingus.tellingme.presentation.ui.feature.myspace.MySpaceScreen
import com.tellingus.tellingme.presentation.ui.feature.otherspace.OtherSpaceScreen
import com.tellingus.tellingme.presentation.ui.feature.otherspace.detail.OtherSpaceDetailScreen
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun TellingMeScreen(
    navController: NavHostController,
    viewModel: TellingMeViewModel = hiltViewModel(),
    startDestination: String = HomeDestinations.HOME,
    uri: Uri? = null,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val coroutineScope = rememberCoroutineScope()

    val bottomNavNotIncludeList = listOf(
        HomeDestinations.RECORD,
        HomeDestinations.ALARM
    )

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in bottomNavNotIncludeList) {
                TellingMeTabBar(
                    currentDestination = currentDestination,
                    navigateToScreen = { navigationItem ->
                        navigateBottomNavigationScreen(
                            navController = navController,
                            navigationItem = navigationItem
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
//                startDestination = HomeDestinations.ROUTE
                startDestination = HomeDestinations.HOME
            ) {
                composable(route = AuthDestinations.Login.LOGIN) {
//                    LoginScreen(navController)
//                    LoginScreen(
//                        navigateToOauthJoinScreen = {
//                            navController.navigate(ScreenRoute.LOGIN.route)
//                        },
//                        navigateToHomeScreen = {
//                            navController.navigate(ScreenRoute.HOME.route)
//                        }
//                    )
                }
                composable(route = HomeDestinations.HOME) {
//                    HomeScreen(navController)
                    HomeScreen(
                        navController = navController,
                        navigateToOtherSpace = { id ->
                            navController.navigate("${OtherSpaceDestinations.OTHER_SPACE}/$id")
                        }
                    )
                }
                composable(route = HomeDestinations.RECORD) {
                    RecordScreen(
                        navController = navController
                    )
                }
                composable(route = MySpaceDestinations.MY_SPACE) {
                    MySpaceScreen(
                        navigateToRecordScreen = {
                            navController.navigate(HomeDestinations.RECORD)
                        },
                    )
                }
                composable(route = OtherSpaceDestinations.OTHER_SPACE) {
                    OtherSpaceScreen()
                }
                composable(
                    route = "${OtherSpaceDestinations.OTHER_SPACE}/{$KEY_ID}",
                    arguments = listOf(
                        navArgument(KEY_ID) {
                            type = NavType.StringType
                        }
                    )
                ) {
                    OtherSpaceDetailScreen(
                        navigateToOtherSpaceScreen = {
                            navController.navigate(OtherSpaceDestinations.OTHER_SPACE)
                        }
                    )
                }
                composable(route = MyPageDestinations.MY_PAGE) {
                    MyPageScreen(navigateToAlarmScreen = {
                        navController.navigate(HomeDestinations.ALARM)
                    })
                }
                composable(route = HomeDestinations.ALARM) {
                    AlarmScreen(navigateToPreviousScreen = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel.tellingMeUiEffect) {
        viewModel.tellingMeUiEffect.collectLatest { tellingMeUiEffect ->
            when (tellingMeUiEffect) {
                is TellingMeUiEffect.MoveToLogin -> {

                }
            }
        }
    }
}

fun navigateBottomNavigationScreen(
    navController: NavHostController,
    navigationItem: BottomNavigationItem,
) {
    // ...
    navController.navigate(navigationItem.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
//        restoreState = true
    }
}

@Composable
fun TellingMeTabBar(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigationItem) -> Unit,
) {
    BottomNavigation(backgroundColor = Color.White) {
        BottomNavigationItem.values().forEach { navigationItem ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navigationItem.icon),
                        contentDescription = null,
                        tint = if (navigationItem.route == currentDestination?.route) Gray500 else Gray200,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(
                            navigationItem.title
                        ),
                        fontSize = 10.sp,
                        color = when (navigationItem.route) {
                            currentDestination?.route -> Gray600
                            else -> Gray300
                        }
                    )
                },
                onClick = { navigateToScreen(navigationItem) },
                selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                selectedContentColor = Primary400,
            )
        }
    }

}

const val KEY_ID = "key-id"