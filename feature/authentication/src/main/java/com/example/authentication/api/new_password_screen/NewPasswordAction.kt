package com.example.authentication.api.new_password_screen

import com.example.common.mvi.MviAction

sealed interface NewPasswordAction : MviAction {

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