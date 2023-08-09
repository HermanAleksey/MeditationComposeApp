package com.justparokq.core.authentication_source.api.model

data class ResetPasswordRequest (
    val email: String,
    val password: String,
)