package com.tellingus.tellingme.presentation.ui.common.navigation

object AuthDestinations {
    const val ROUTE = "authRoute"

    const val SPLASH = "splash"

    object Login {
        const val ROUTE = "loginRoute"

        const val LOGIN = "login"
    }

    object SignUp {
        const val ROUTE = "signUpRoute"

        const val PHONE = "signUpPhone"
        const val AUTHENTICATION = "signUpAuthentication"
        const val FAVORITE_TASTE = "signUpFavoriteTaste"
        const val COMPLETE = "signUpComplete"
    }
}

object HomeDestinations {
    const val ROUTE = "homeRoute"

    const val HOME = "home"

    const val RECORD = "record"
    const val ALARM = "alarm"
}

object MySpaceDestinations {
    const val ROUTE = "mySpaceRoute"

    const val MY_SPACE = "mySpace"
}

object OtherSpaceDestinations {
    const val ROUTE = "otherSpaceRoute"

    const val OTHER_SPACE = "otherSpace"
}

object MyPageDestinations {
    const val ROUTE = "myPageSpaceRoute"

    const val MY_PAGE = "myPageSpace"
}