package com.example.meditationcomposeapp.model.utils.validation

class PasswordField(
    value: String
) : InputTextField(value) {

    override val REGEX: String =
        """^(?=.*[A-Z].*[A-Z])(?=.*[!@#${'$'}&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}${'$'}"""

    override fun specialFieldValidation(): ValidationResult {
        //TODO implement special validation
        return ValidationResult(successful = true)
    }
}