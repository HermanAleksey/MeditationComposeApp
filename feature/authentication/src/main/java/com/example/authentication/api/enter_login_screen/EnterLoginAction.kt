package com.example.authentication.api.enter_login_screen

import com.justparokq.core.common.mvi.MviAction

sealed interface EnterLoginAction : MviAction {

    data class FirstLaunch(
        val initialLogin: String
    ): EnterLoginAction

    object ConfirmClick: EnterLoginAction

    data class LoginTextChanged(
        val text: String
    ): EnterLoginAction
}