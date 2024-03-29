package com.justparokq.mediose.presentation.ui_controls.toolbar

import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavRoute

class ToolbarNavDependencies(
    val navigateToUpdatesHistory: () -> Unit,
    val navigateToEnterScreen: () -> Unit,
) : NavDependencies

sealed class ToolbarNavRoute : NavRoute<ToolbarNavDependencies>() {

    object UpdatesHistory : ToolbarNavRoute() {
        override fun navigate(navDependencies: ToolbarNavDependencies) {
            navDependencies.navigateToUpdatesHistory()
        }
    }

    object EnterScreen : ToolbarNavRoute() {
        override fun navigate(navDependencies: ToolbarNavDependencies) {
            navDependencies.navigateToEnterScreen()
        }
    }
}
