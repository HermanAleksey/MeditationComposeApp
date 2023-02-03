package com.example.authentication.internal.validation

class NameField(
    value: String
) : InputTextField(value) {

    override val REGEX = """.+"""

    override fun specialFieldValidation(): ValidationResult {
        //TODO implement special validation
        return ValidationResult(successful = true)
    }
}