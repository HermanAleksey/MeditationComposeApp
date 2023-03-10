package com.example.authentication.api.registration_screen

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class RegistrationScreenNavDependencies(
    val navigateToLoginScreen: () -> Unit,
) : NavDependencies

sealed class RegistrationScreenNavRoute : NavRoute<RegistrationScreenNavDependencies>() {

    object LoginScreen : RegistrationScreenNavRoute() {
        override fun navigate(navDependencies: RegistrationScreenNavDependencies) {
            navDependencies.navigateToLoginScreen()
        }
    }
}
