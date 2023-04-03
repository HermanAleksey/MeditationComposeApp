package com.example.authentication.internal.screens.login

import com.example.common.mvi.MviAction

internal sealed interface LoginAction : MviAction {

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
