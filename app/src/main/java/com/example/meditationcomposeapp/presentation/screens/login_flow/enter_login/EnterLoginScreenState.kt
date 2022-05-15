package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import com.example.meditationcomposeapp.model.utils.resources.UiText

data class EnterLoginScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    val emailError: UiText? = null
)