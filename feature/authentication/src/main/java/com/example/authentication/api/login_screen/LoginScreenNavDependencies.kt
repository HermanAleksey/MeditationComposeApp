package com.example.authentication.api.login_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class LoginScreenNavDependencies(
    val navigateToMainScreen: () -> Unit,
    val navigateToEnterLoginScreen: (login: String) -> Unit,
    val navigateToRegistrationScreen: () -> Unit,
) : NavDependencies

sealed class LoginScreenNavRoute : NavRoute<LoginScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: LoginScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    class MainScreen : LoginScreenNavRoute() {
        override fun navigate(navDependencies: LoginScreenNavDependencies) {
            navDependencies.navigateToMainScreen()
        }
    }

    class EnterLoginScreen(private val login: String) : LoginScreenNavRoute() {
        override fun navigate(navDependencies: LoginScreenNavDependencies) {
            navDependencies.navigateToEnterLoginScreen(login)
        }
    }

    class RegistrationScreen : LoginScreenNavRoute() {
        override fun navigate(navDependencies: LoginScreenNavDependencies) {
            navDependencies.navigateToRegistrationScreen()
        }
    }
}
