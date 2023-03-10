package com.example.authentication.internal.screens.enter_login

import com.example.common.utils.UiText

data class EnterLoginScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val loginError: UiText? = null
)