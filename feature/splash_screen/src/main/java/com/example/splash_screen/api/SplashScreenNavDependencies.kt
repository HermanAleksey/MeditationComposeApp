package com.example.splash_screen.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class SplashScreenNavDependencies(
    val navigateToMainScreen: () -> Unit,
    val navigateToEnterScreen: () -> Unit,
) : NavDependencies

sealed class SplashScreenNavRoute : NavRoute<SplashScreenNavDependencies>() {

    object EnterScreen : SplashScreenNavRoute() {
        override fun navigate(navDependencies: SplashScreenNavDependencies) {
            navDependencies.navigateToEnterScreen()
        }
    }

    object MainScreen : SplashScreenNavRoute() {
        override fun navigate(navDependencies: SplashScreenNavDependencies) {
            navDependencies.navigateToMainScreen()
        }
    }
}
