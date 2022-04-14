package com.example.meditationcomposeapp.model.network

data class RegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
)