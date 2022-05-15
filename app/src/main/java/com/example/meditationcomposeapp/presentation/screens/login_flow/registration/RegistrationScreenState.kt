package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import com.example.meditationcomposeapp.model.utils.resources.UiText

data class RegistrationScreenState(
    val isLoading: Boolean = false,
    val name: String = "",
    val nameError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null
)