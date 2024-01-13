package com.tellingus.tellingme.presentation.ui.feature

import android.annotation.SuppressLint
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.feature.home.HomeScreen
import com.tellingus.tellingme.presentation.ui.feature.home.record.RecordScreen
import com.tellingus.tellingme.presentation.ui.feature.mypage.MyPageScreen
import com.tellingus.tellingme.presentation.ui.feature.myspace.MySpaceScreen
import com.tellingus.tellingme.presentation.ui.feature.otherspace.OtherSpaceScreen
import com.tellingus.tellingme.presentation.ui.feature.otherspace.detail.OtherSpaceDetailScreen
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TellingMeScreen(
    navController: NavHostController = rememberNavController(),
    uri: Uri? = null,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                TellingMeBottomSheetContent()
            }
        },
        sheetState = sheetState,
        scrimColor = ModalBottomSheetDefaults.scrimColor,
    ) {
        Scaffold(
            bottomBar = {
                if (currentDestination?.route != TellingMeScreenRoute.RECORD.route) {
                    TellingMeTabBar(
                        currentDestination = currentDestination,
                        navigateToScreen = { navigationItem ->
                            navigateBottomNavigationScreen(
                                navController = navController,
                                navigationItem = navigationItem
                            )
                        })
                }
            }) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHost(
                    navController = navController,
                    startDestination = TellingMeScreenRoute.HOME.route
                ) {
                    composable(route = TellingMeScreenRoute.HOME.route) {
                        HomeScreen(
                            navigateToRecordScreen = {
                                navController.navigate(TellingMeScreenRoute.RECORD.route)
                            },
                            navigateToOtherSpace = { id ->
                                navController.navigate("${TellingMeScreenRoute.OTHER_SPACE.route}/$id")
                            })
                    }
                    composable(route = TellingMeScreenRoute.RECORD.route) {
                        RecordScreen(
                            navigateToPreviousScreen = {
                                navController.popBackStack()
                            })
                    }
                    composable(route = TellingMeScreenRoute.MY_SPACE.route) {
                        MySpaceScreen(
                            navigateToRecordScreen = {
                                navController.navigate(
                                    TellingMeScreenRoute.RECORD.route
                                )
                            },
                        )
                    }
                    composable(route = TellingMeScreenRoute.OTHER_SPACE.route) {
                        OtherSpaceScreen()
                    }
                    composable(
                        route = "${TellingMeScreenRoute.OTHER_SPACE.route}/{$KEY_ID}",
                        arguments = listOf(
                            navArgument(KEY_ID) {
                                type = NavType.StringType
                            }
                        )) {
                        OtherSpaceDetailScreen(
                            navigateToOtherSpaceScreen = {
                                navController.navigate(TellingMeScreenRoute.OTHER_SPACE.route)
                            })
                    }
                    composable(route = TellingMeScreenRoute.MY_PAGE.route) {
                        MyPageScreen()
                    }
                }

            }

        }
    }
}


fun navigateBottomNavigationScreen(
    navController: NavHostController,
    navigationItem: TellingMeBottomNavigationItem,
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
    navigateToScreen: (TellingMeBottomNavigationItem) -> Unit,
) {
    BottomNavigation(backgroundColor = Color.White) {
        TellingMeBottomNavigationItem.values().forEach { navigationItem ->
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

@Composable
fun TellingMeBottomSheetContent() {
    Text(text = "TellingMe Bottom Sheet Content")
}

enum class TellingMeBottomNavigationItem(
    val route: String, @DrawableRes val icon: Int, @StringRes val title: Int
) {
    HOME(
        route = TellingMeScreenRoute.HOME.route,
        icon = R.drawable.icon_home,
        title = R.string.navigation_home_text
    ),
    MY_SPACE(
        route = TellingMeScreenRoute.MY_SPACE.route,
        icon = R.drawable.icon_my_space,
        title = R.string.navigation_my_space_text
    ),
    OTHER_SPACE(
        route = TellingMeScreenRoute.OTHER_SPACE.route,
        icon = R.drawable.icon_other_space,
        title = R.string.navigation_other_space_text
    ),
    MY_PAGE(
        route = TellingMeScreenRoute.MY_PAGE.route,
        icon = R.drawable.icon_user,
        title = R.string.navigation_my_page_text
    ),
}

enum class TellingMeScreenRoute(val route: String) {
    HOME("home"),
    MY_SPACE("my-space"),
    OTHER_SPACE("other-space"),
    MY_PAGE("my-page"),
    RECORD("record")
}

const val KEY_ID = "key-id"