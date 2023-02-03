package com.example.authentication.internal.validation

import com.example.common.utils.UiText
import com.example.feature.authentication.R

abstract class InputTextField(
    val value: String
) {
    protected open val REGEX = """.+"""

    abstract fun specialFieldValidation(): ValidationResult

    fun validate(): ValidationResult {
        if (value.isBlank())
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_field_is_blank)
            )

        if (value.length > MAX_FIELD_LENGTH)
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.error_field_is_long)
            )

        return specialFieldValidation()
    }

    companion object {
        private const val MAX_FIELD_LENGTH = 10
    }
}