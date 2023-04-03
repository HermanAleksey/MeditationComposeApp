package com.example.authentication.internal.screens.login

import com.example.common.mvi.MviAction

internal sealed interface LoginAction : MviAction {

    data class OnLoginTextChanged(
        val text: String,
    ) : LoginAction

    data class OnPasswordTextChanged(
        val text: String,
    ) : LoginAction

    object OnForgotPasswordClicked : LoginAction

    object OnLoginClicked : LoginAction

    object OnSignUpClicked : LoginAction
}
