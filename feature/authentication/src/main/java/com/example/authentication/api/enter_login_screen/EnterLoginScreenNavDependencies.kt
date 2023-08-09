package com.example.authentication.api.enter_login_screen

import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavRoute

class EnterLoginScreenNavDependencies(
    val navigateEnterCodeScreen: (login: String) -> Unit,
) : NavDependencies

sealed class EnterLoginScreenNavRoute : NavRoute<EnterLoginScreenNavDependencies>() {

    data class EnterCodeScreen(private val login: String) : EnterLoginScreenNavRoute() {
        override fun navigate(navDependencies: EnterLoginScreenNavDependencies) {
            navDependencies.navigateEnterCodeScreen(login)
        }
    }
}
