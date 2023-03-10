package com.example.authentication.api.new_password_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class NewPasswordScreenNavDependencies(
    val navigateToLoginScreen: () -> Unit,
) : NavDependencies

sealed class NewPasswordScreenNavRoute : NavRoute<NewPasswordScreenNavDependencies>() {

    object LoginScreen : NewPasswordScreenNavRoute() {
        override fun navigate(navDependencies: NewPasswordScreenNavDependencies) {
            navDependencies.navigateToLoginScreen()
        }
    }
}
