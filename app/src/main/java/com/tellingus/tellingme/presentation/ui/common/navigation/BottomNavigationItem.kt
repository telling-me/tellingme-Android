package com.tellingus.tellingme.presentation.ui.common.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.feature.TellingMeScreenRoute

enum class BottomNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
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