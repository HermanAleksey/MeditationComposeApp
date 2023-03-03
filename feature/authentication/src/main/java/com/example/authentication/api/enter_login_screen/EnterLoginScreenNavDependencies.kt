package com.example.authentication.api.enter_login_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class EnterLoginScreenNavDependencies(
    val navigateEnterCodeScreen: (login: String) -> Unit,
) : NavDependencies

sealed class EnterLoginScreenNavRoute : NavRoute<EnterLoginScreenNavDependencies> {
    override var navigationHasBeenHandled: Boolean = false

    override fun tryNavigate(navDependencies: EnterLoginScreenNavDependencies) {
        return if (navigationHasBeenHandled) {
            return
        } else {
            navigationHasBeenHandled = true
            navigate(navDependencies)
        }
    }

    data class EnterCodeScreen(private val login: String) : EnterLoginScreenNavRoute() {
        override fun navigate(navDependencies: EnterLoginScreenNavDependencies) {
            navDependencies.navigateEnterCodeScreen(login)
        }
    }
}
