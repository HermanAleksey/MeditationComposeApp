package com.example.meditationcomposeapp.presentation.screens.login_flow.new_password

data class NewPasswordScreenState(
    val isLoading: Boolean = false,
    val newPassword: String = "",
    val repeatPassword: String = "",
)