package com.justparokq.core.authentication_source.api.model

data class RegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
)