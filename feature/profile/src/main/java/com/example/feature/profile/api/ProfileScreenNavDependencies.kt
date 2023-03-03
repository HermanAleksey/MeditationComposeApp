package com.example.feature.profile.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class ProfileScreenNavDependencies(
    val navigateToEnterScreen: () -> Unit,
) : NavDependencies

sealed class ProfileScreenNavRoute : NavRoute<ProfileScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: ProfileScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    object EnterScreen : ProfileScreenNavRoute() {
        override fun navigate(navDependencies: ProfileScreenNavDependencies) {
            navDependencies.navigateToEnterScreen()
        }
    }
}
