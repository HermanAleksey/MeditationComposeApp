package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

data class RegistrationScreenState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val password: String = ""
)