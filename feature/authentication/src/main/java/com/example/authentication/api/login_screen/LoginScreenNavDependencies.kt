package com.example.authentication.api.login_screen

import android.util.Log
import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class LoginScreenNavDependencies(
    val navigateToMainScreen: () -> Unit,
    val navigateToEnterLoginScreen: (login: String) -> Unit,
    val navigateToRegistrationScreen: () -> Unit,
) : NavDependencies

sealed class LoginScreenNavRoute : NavRoute<LoginScreenNavDependencies>() {
    object MainScreen : LoginScreenNavRoute() {
        override fun navigate(navDependencies: LoginScreenNavDependencies) {
            navDependencies.navigateToMainScreen()
        }
    }

    data class EnterLoginScreen(private val login: String) : LoginScreenNavRoute() {
        override fun navigate(navDependencies: LoginScreenNavDependencies) {
            navDependencies.navigateToEnterLoginScreen(login)
        }
    }

    object RegistrationScreen : LoginScreenNavRoute() {
        override fun navigate(navDependencies: LoginScreenNavDependencies) {
            Log.e("TAGG", "toRegistration: ")
            navDependencies.navigateToRegistrationScreen()
        }
    }
}
