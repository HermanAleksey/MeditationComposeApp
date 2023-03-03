package com.example.authentication.api.enter_code_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class EnterCodeScreenNavDependencies(
    val navigateToNewPasswordScreen: (login: String) -> Unit,
) : NavDependencies

sealed class EnterCodeScreenNavRoute : NavRoute<EnterCodeScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: EnterCodeScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    data class NewPasswordScreen(private val login: String) : EnterCodeScreenNavRoute() {
        override fun navigate(navDependencies: EnterCodeScreenNavDependencies) {
            navDependencies.navigateToNewPasswordScreen(login)
        }
    }
}
