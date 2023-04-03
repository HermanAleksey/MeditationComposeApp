package com.example.authentication.internal.screens.new_password

import com.example.common.mvi.MviAction

internal sealed interface NewPasswordAction : MviAction {

    data class FirstLaunch(
        val login: String,
    ) : NewPasswordAction

    data class NewPasswordTextChanged(
        val text: String,
    ) : NewPasswordAction

    data class RepeatPasswordTextChanged(
        val text: String,
    ) : NewPasswordAction

    object ConfirmClick : NewPasswordAction
}