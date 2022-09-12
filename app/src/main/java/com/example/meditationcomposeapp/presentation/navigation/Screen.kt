package com.example.meditationcomposeapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    open class BottomNavBarScreenItem(
        route: String,
        val title: String,
        val icon: ImageVector
    ) : Screen(route)

    object Splash : Screen("com.example.meditationcomposeapp.screens.SPLASH_SCREEN")

    object Enter : Screen("com.example.meditationcomposeapp.screens.ENTER_SCREEN")
    object Login : Screen("com.example.meditationcomposeapp.screens.LOGIN_SCREEN")
    object Registration : Screen("com.example.meditationcomposeapp.screens.REGISTRATION_SCREEN")

    object EnterLogin : Screen("com.example.meditationcomposeapp.screens.ENTER_LOGIN_SCREEN")
    object EnterCode : Screen("com.example.meditationcomposeapp.screens.ENTER_CODE_SCREEN")
    object NewPassword : Screen("com.example.meditationcomposeapp.screens.NEW_PASSWORD_SCREEN")

    object Main : BottomNavBarScreenItem(
        route = "com.example.meditationcomposeapp.screens.MAIN_SCREEN",
        title = "Main",
        icon = Icons.Default.Warning
    )
    object Screen2 : BottomNavBarScreenItem(
        route = "com.example.meditationcomposeapp.screens.SCREEN_2TEST",
        title = "Screen_2",
        icon = Icons.Default.AccountBox
    )
    object Screen3 : BottomNavBarScreenItem(
        route = "com.example.meditationcomposeapp.screens.SCREEN_3TEST",
        title = "Screen_2",
        icon = Icons.Default.Email
    )
}


