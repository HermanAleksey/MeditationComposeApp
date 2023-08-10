package com.justparokq.feature.authentication.internal.validation

import com.justparokq.core.common.utils.UiText

internal data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)