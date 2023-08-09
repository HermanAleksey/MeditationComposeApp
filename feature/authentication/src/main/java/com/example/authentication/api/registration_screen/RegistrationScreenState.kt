package com.example.authentication.api.registration_screen

import com.justparokq.core.common.mvi.MviState
import com.justparokq.core.common.utils.UiText

data class RegistrationScreenState(
    val isLoading: Boolean = false,
    val name: String = "",
    val nameError: UiText? = null,
    val login: String = "",
    val loginError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null
): MviState