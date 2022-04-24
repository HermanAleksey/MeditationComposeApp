package com.example.meditationcomposeapp.presentation.screens.login_flow.newpassword

data class NewPasswordScreenState(
    val isLoading: Boolean = false,
    val newPassword: String = "",
    val repeatPassword: String = "",
)