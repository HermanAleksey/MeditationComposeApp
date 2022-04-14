package com.example.meditationcomposeapp.data_source.repository.authentication

import com.example.meditationcomposeapp.data_source.network.AuthenticationApi
import com.example.meditationcomposeapp.model.network.RegistrationRequest
import com.example.meditationcomposeapp.model.network.UpdatePasswordRequest
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationApi: AuthenticationApi
): AuthenticationRepository {
    override fun login(login: String, password: String) {
        authenticationApi.login(login, password)
    }

    override fun registrate(name: String, login: String, password: String) {
        authenticationApi.registration(
            RegistrationRequest(name, login, password)
        )
    }

    override fun requestPasswordRestoration(login: String) {
        authenticationApi.requestPasswordRestore(login)
    }

    override fun setNewPassword(token: String, newPassword: String) {
        authenticationApi.setNewPassword(token, UpdatePasswordRequest(newPassword))
    }

    override fun verifyCode(login: String, code: String) {
        authenticationApi.verifyCode(login, code)
    }
}