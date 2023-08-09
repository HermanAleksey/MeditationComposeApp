package com.justparokq.core.authentication_source.api.model

data class LoginRequest(
    val email: String,
    val password: String,
)