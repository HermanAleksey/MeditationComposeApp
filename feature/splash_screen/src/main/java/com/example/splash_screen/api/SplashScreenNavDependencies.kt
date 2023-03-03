package com.example.splash_screen.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class SplashScreenNavDependencies(
    val navigateToMainScreen: () -> Unit,
    val navigateToEnterScreen: () -> Unit,
) : NavDependencies

sealed class SplashScreenNavRoute : NavRoute<SplashScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: SplashScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

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
