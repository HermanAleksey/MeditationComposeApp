package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import com.example.meditationcomposeapp.model.utils.resources.UiText

data class LoginScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
)