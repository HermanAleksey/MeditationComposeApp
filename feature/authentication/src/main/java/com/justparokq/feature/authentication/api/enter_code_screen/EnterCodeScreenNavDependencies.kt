package com.justparokq.feature.authentication.api.enter_code_screen

import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavRoute

class EnterCodeScreenNavDependencies(
    val navigateToNewPasswordScreen: (login: String) -> Unit,
) : NavDependencies

sealed class EnterCodeScreenNavRoute : NavRoute<EnterCodeScreenNavDependencies>() {

    data class NewPasswordScreen(private val login: String) : EnterCodeScreenNavRoute() {
        override fun navigate(navDependencies: EnterCodeScreenNavDependencies) {
            navDependencies.navigateToNewPasswordScreen(login)
        }
    }
}
