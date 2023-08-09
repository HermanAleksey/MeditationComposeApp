package com.example.authentication.api.login_screen

import com.justparokq.core.common.mvi.MviAction

sealed interface LoginAction : MviAction {

    data class LoginTextChanged(
        val text: String,
    ) : LoginAction

    data class PasswordTextChanged(
        val text: String,
    ) : LoginAction

    object ForgotPasswordClick : LoginAction

    object LoginClick : LoginAction

    object SignUpClick : LoginAction
}
