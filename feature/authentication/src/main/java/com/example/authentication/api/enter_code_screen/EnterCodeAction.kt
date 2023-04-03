package com.example.authentication.api.enter_code_screen

import com.example.common.mvi.MviAction

sealed interface EnterCodeAction : MviAction {

    data class FirstLaunch(
        val login: String
    ) : EnterCodeAction

    data class CodeDigitChanged(
        val position: Int,
        val number: Int,
    ) : EnterCodeAction

    object LastDigitFilled : EnterCodeAction
}