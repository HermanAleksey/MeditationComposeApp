package com.justparokq.feature.authentication.api.new_password_screen

import com.justparokq.core.common.mvi.MviState
import com.justparokq.core.common.utils.UiText

data class NewPasswordScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val newPassword: String = "",
    val newPasswordError: UiText? = null,
    val repeatPassword: String = "",
    val repeatPasswordError: UiText? = null
): MviState