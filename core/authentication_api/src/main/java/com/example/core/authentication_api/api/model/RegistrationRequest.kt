package com.example.core.authentication_api.api.model

data class RegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
)