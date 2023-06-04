package com.example.core.authentication_source.api.model

data class VerifyCodeRequest(
    val email: String,
    val code: String,
)