package com.example.meditationcomposeapp.model.utils.validation

import com.example.meditationcomposeapp.model.utils.resources.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)