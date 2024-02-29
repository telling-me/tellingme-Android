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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.BottomNavigationItem
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.MyPageDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.MySpaceDestinations
import com.tellingus.tellingme.presentation.ui.common.navigation.OtherSpaceDestinations
import com.tellingus.tellingme.presentation.ui.feature.auth.navigation.authGraph
import com.tellingus.tellingme.presentation.ui.feature.home.homeGraph
import com.tellingus.tellingme.presentation.ui.feature.mypage.myPageGraph
import com.tellingus.tellingme.presentation.ui.feature.myspace.mySpaceGraph
import com.tellingus.tellingme.presentation.ui.feature.otherspace.otherSpaceGraph
import com.tellingus.tellingme.presentation.ui.theme.Gray200
import com.tellingus.tellingme.presentation.ui.theme.Gray300
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun TellingMeNavHost(
    navController: NavHostController,
    viewModel: TellingMeViewModel = hiltViewModel(),
    startDestination: String = AuthDestinations.ROUTE,
    uri: Uri? = null,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavNotIncludeList = listOf(
        HomeDestinations.HOME,
        MySpaceDestinations.MY_SPACE,
        OtherSpaceDestinations.OTHER_SPACE,
        MyPageDestinations.MY_PAGE
    )

    Scaffold(
        bottomBar = {
            if (currentDestination?.route in bottomNavNotIncludeList) {
                TellingMeBottomNavigationBar(
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                authGraph(
                    navController = navController
                )
                homeGraph(
                    navController = navController
                )
                mySpaceGraph(
                    navController = navController
                )
                otherSpaceGraph(
                    navController = navController
                )
                myPageGraph(
                    navController = navController
                )
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

@Composable
fun TellingMeBottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
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
                        text = stringResource(navigationItem.title),
                        fontSize = 10.sp,
                        color = when (navigationItem.route) {
                            currentDestination?.route -> Gray600
                            else -> Gray300
                        }
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                selectedContentColor = Primary400,
                onClick = {
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true    // 최상위 아이템은 백스택에 쌓지 않기
                        }
                        launchSingleTop = true    // 동일한 항목에 대한 중복 X
                        restoreState = true    // 이전 아이템 클릭 시 상태 복원
                    }
                }
            )
        }
    }
}
