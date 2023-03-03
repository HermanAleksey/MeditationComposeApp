package com.example.authentication.api.new_password_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class NewPasswordScreenNavDependencies(
    val navigateToLoginScreen: () -> Unit,
) : NavDependencies

sealed class NewPasswordScreenNavRoute : NavRoute<NewPasswordScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: NewPasswordScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    object LoginScreen : NewPasswordScreenNavRoute() {
        override fun navigate(navDependencies: NewPasswordScreenNavDependencies) {
            navDependencies.navigateToLoginScreen()
        }
    }
}
