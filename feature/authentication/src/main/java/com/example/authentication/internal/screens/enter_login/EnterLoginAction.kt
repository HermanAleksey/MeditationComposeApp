package com.example.authentication.internal.screens.enter_login

import com.example.common.mvi.MviAction

internal sealed interface EnterLoginAction : MviAction {

    data class FirstLaunch(
        val initialLogin: String
    ): EnterLoginAction

    object ConfirmClick: EnterLoginAction

    data class LoginTextChanged(
        val text: String
    ): EnterLoginAction
}