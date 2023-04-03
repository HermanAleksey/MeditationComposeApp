package com.example.authentication.internal.screens.registration

import com.example.common.mvi.MviAction

internal sealed interface RegistrationAction : MviAction {

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