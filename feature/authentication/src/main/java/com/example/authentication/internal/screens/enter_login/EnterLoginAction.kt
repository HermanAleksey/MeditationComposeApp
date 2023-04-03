package com.example.authentication.internal.screens.enter_login

import com.example.common.mvi.MviAction

internal sealed interface EnterLoginAction : MviAction {

    data class OnScreenEntered(
        val initialLogin: String
    ): EnterLoginAction

    object OnConfirmClick: EnterLoginAction

    data class OnLoginTextChanged(
        val text: String
    ): EnterLoginAction
}