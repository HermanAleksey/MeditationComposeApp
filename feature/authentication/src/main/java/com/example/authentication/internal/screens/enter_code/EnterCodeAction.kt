package com.example.authentication.internal.screens.enter_code

import com.example.common.mvi.MviAction

internal sealed interface EnterCodeAction : MviAction {

    data class FirstLaunch(
        val login: String
    ) : EnterCodeAction

    data class CodeDigitChanged(
        val position: Int,
        val number: Int,
    ) : EnterCodeAction

    object LastDigitFilled : EnterCodeAction
}