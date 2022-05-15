package com.example.meditationcomposeapp.presentation.screens.login_flow.new_password

import com.example.meditationcomposeapp.model.utils.resources.UiText

data class NewPasswordScreenState(
    val isLoading: Boolean = false,
    val newPassword: String = "",
    val newPasswordError: UiText? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: UiText? = null
)