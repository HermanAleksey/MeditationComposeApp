package com.example.meditationcomposeapp.model.utils.validation

import com.example.meditationcomposeapp.presentation.screens.destinations.DirectionDestination

class NameField(
    value: String
) : InputTextField(value) {

    override val REGEX = """.+"""

    override fun specialFieldValidation(): ValidationResult {
        //TODO implement special validation
        DirectionDestination
        return ValidationResult(successful = true)
    }
}