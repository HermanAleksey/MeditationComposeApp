package com.example.meditationcomposeapp.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("com.example.meditationcomposeapp.screens.SPLASH_SCREEN")

    object Enter : Screen("com.example.meditationcomposeapp.screens.ENTER_SCREEN")
    object Login : Screen("com.example.meditationcomposeapp.screens.LOGIN_SCREEN")
    object Registration : Screen("com.example.meditationcomposeapp.screens.REGISTRATION_SCREEN")

    object EnterLogin : Screen("com.example.meditationcomposeapp.screens.ENTER_LOGIN_SCREEN")
    object RestorePassword : Screen("com.example.meditationcomposeapp.screens.RESTORE_PASSWORD_SCREEN")
    object NewPassword : Screen("com.example.meditationcomposeapp.screens.NEW_PASSWORD_SCREEN")



    object Main : Screen("com.example.meditationcomposeapp.screens.MAIN_SCREEN")
}

