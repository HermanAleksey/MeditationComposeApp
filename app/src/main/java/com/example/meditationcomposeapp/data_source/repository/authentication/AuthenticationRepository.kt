package com.example.meditationcomposeapp.data_source.repository.authentication

interface AuthenticationRepository {
    fun login(login: String, password: String)

    fun registrate(name: String, login: String, password: String)

    fun requestPasswordRestoration(login: String)

    fun setNewPassword(token: String, newPassword: String)

    fun verifyCode(login: String, code: String)
}