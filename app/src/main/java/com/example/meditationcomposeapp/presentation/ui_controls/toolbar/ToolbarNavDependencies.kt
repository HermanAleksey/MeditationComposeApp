package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class ToolbarNavDependencies(
    val navigateToUpdatesHistory: () -> Unit,
) : NavDependencies

sealed class ToolbarNavRoute : NavRoute<ToolbarNavDependencies>() {

    object UpdatesHistory : ToolbarNavRoute() {
        override fun navigate(navDependencies: ToolbarNavDependencies) {
            navDependencies.navigateToUpdatesHistory()
        }
    }
}
