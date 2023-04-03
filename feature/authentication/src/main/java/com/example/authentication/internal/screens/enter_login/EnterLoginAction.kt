package com.example.authentication.internal.screens.enter_login

import com.example.common.mvi.Action

internal sealed interface EnterLoginAction : Action {

    data class OnScreenEntered(
        val initialLogin: String
    ): EnterLoginAction

    object OnConfirmClick: EnterLoginAction

    data class OnLoginTextChanged(
        val text: String
    ): EnterLoginAction
}