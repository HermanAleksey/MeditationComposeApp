package com.example.authentication.api.new_password_screen

import com.example.common.mvi.MviState
import com.example.common.utils.UiText

data class NewPasswordScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val newPassword: String = "",
    val newPasswordError: UiText? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: UiText? = null
): MviState