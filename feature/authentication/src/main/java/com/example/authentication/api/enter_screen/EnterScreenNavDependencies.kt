package com.example.authentication.api.enter_screen

import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavRoute

class EnterScreenNavDependencies(
    val navigateToLoginScreen: () -> Unit,
    val navigateToRegistrationScreen: () -> Unit,
) : NavDependencies

sealed class EnterScreenNavRoute : NavRoute<EnterScreenNavDependencies>() {

    object LoginScreen : EnterScreenNavRoute() {
        override fun navigate(navDependencies: EnterScreenNavDependencies) {
            navDependencies.navigateToLoginScreen()
        }
    }

    object RegistrationScreen : EnterScreenNavRoute() {
        override fun navigate(navDependencies: EnterScreenNavDependencies) {
            navDependencies.navigateToRegistrationScreen()
        }
    }
}
