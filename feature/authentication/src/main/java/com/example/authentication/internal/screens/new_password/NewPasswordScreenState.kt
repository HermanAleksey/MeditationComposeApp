package com.example.authentication.internal.screens.new_password

import com.example.common.utils.UiText

data class NewPasswordScreenState(
    val isLoading: Boolean = false,
    val newPassword: String = "",
    val newPasswordError: UiText? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: UiText? = null
)