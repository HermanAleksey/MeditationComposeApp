package com.example.feature.profile.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class ProfileScreenNavDependencies(
    val navigateToEnterScreen: () -> Unit,
) : NavDependencies

sealed class ProfileScreenNavRoute : NavRoute<ProfileScreenNavDependencies>() {

    object EnterScreen : ProfileScreenNavRoute() {
        override fun navigate(navDependencies: ProfileScreenNavDependencies) {
            navDependencies.navigateToEnterScreen()
        }
    }
}
