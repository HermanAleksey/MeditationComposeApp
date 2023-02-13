package com.example.authentication.internal.validation

internal class LoginField(
    value: String
) : InputTextField(value) {

    override val REGEX = """[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+.+.[a-zA-Z]{2,4}"""

    override fun specialFieldValidation(): ValidationResult {
        //TODO implement special validation
        return ValidationResult(successful = true)
    }
}