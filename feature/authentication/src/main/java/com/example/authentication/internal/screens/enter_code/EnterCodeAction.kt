package com.example.authentication.internal.screens.enter_code

import com.example.common.mvi.Action

internal sealed interface EnterCodeAction : Action {

    data class OnScreenEntered(
        val login: String
    ) : EnterCodeAction

    data class OnCodeDigitChanged(
        val position: Int,
        val number: Int,
    ) : EnterCodeAction

    object OnLastDigitFilled : EnterCodeAction
}