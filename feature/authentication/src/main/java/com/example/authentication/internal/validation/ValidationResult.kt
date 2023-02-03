package com.example.authentication.internal.validation

import com.example.common.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)