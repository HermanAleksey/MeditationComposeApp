package com.justparokq.feature.authentication.api.enter_login_screen

import com.justparokq.core.common.mvi.MviState
import com.justparokq.core.common.utils.UiText

data class EnterLoginScreenState(
    val isLoading: Boolean = false,
    val login: String = "",
    val loginError: UiText? = null
): MviState