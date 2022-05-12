package com.example.meditationcomposeapp.model.utils.validation

import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.utils.resources.UiText

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