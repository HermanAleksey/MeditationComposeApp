package com.example.meditationcomposeapp.presentation.screens.login_flow.login

data class LoginScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = ""
)