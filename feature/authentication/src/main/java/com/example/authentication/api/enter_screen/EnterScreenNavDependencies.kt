package com.example.authentication.api.enter_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class EnterScreenNavDependencies(
    val navigateToLoginScreen: () -> Unit,
    val navigateToRegistrationScreen: () -> Unit,
) : NavDependencies

sealed class EnterScreenNavRoute : NavRoute<EnterScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: EnterScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    class LoginScreen : EnterScreenNavRoute() {
        override fun navigate(navDependencies: EnterScreenNavDependencies) {
            navDependencies.navigateToLoginScreen()
        }
    }

    class RegistrationScreen : EnterScreenNavRoute() {
        override fun navigate(navDependencies: EnterScreenNavDependencies) {
            navDependencies.navigateToRegistrationScreen()
        }
    }
}
