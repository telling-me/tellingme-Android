package com.tellingus.tellingme.presentation.ui.common.navigation

object AuthDestinations {
    const val ROUTE = "authRoute"

    const val SPLASH = "splash"

    object Login {
        const val ROUTE = "loginRoute"

        const val LOGIN = "login"

    }

    object Signup {
        const val ROUTE = "signupRoute"

        const val SIGNUP_NICKNAME = "signupNickname"
        const val SIGNUP_BIRTH_GENDER = "signupBirthGender"
        const val SIGNUP_JOB = "signupJob"
        const val SIGNUP_WORRY = "signupWorry"
    }

}

object HomeDestinations {
    const val ROUTE = "homeRoute"

    const val HOME = "home"
    const val RECORD = "record"
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
    const val ROUTE = "myPageRoute"

    const val MY_PAGE = "myPageSpace"
    const val ALARM = "alarm"

}