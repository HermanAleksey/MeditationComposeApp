package com.example.authentication.internal.screens.login

import com.example.common.utils.UiText

data class LoginScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val loginError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
)