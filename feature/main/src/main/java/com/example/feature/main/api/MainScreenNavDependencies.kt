package com.example.feature.main.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class MainScreenNavDependencies(
    val navigateToPuzzleScreen: () -> Unit,
) : NavDependencies

sealed class MainScreenNavRoute : NavRoute<MainScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: MainScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    class PuzzleScreen : MainScreenNavRoute() {
        override fun navigate(navDependencies: MainScreenNavDependencies) {
            navDependencies.navigateToPuzzleScreen()
        }
    }
}
