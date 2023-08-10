package com.justparokq.feature.authentication.api.login_screen

import com.justparokq.core.common.mvi.MviState
import com.justparokq.core.common.utils.UiText

data class LoginScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val loginError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
): MviState