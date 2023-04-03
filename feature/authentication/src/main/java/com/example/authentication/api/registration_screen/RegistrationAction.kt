package com.example.authentication.api.registration_screen

import com.example.common.mvi.MviAction

sealed interface RegistrationAction : MviAction {

    data class NameTextChanged(
        val text: String,
    ) : RegistrationAction

    data class LoginTextChanged(
        val text: String,
    ) : RegistrationAction

    data class PasswordTextChanged(
        val text: String,
    ) : RegistrationAction

    object SignUpClick: RegistrationAction

    object SignInClick: RegistrationAction
}