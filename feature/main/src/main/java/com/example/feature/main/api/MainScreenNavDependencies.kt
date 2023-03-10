package com.example.feature.main.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class MainScreenNavDependencies(
    val navigateToPuzzleScreen: () -> Unit,
) : NavDependencies

sealed class MainScreenNavRoute : NavRoute<MainScreenNavDependencies>() {

    class PuzzleScreen : MainScreenNavRoute() {
        override fun navigate(navDependencies: MainScreenNavDependencies) {
            navDependencies.navigateToPuzzleScreen()
        }
    }
}
