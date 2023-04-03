package com.example.authentication.api.enter_login_screen

import com.example.common.mvi.State
import com.example.common.utils.UiText

data class EnterLoginScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val loginError: UiText? = null
): State