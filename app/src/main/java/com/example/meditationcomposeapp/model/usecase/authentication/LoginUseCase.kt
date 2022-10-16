package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LoginUseCase {
    operator fun invoke(login: String, password: String): Flow<NetworkResponse<Profile>>
}

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthenticationRepository
) : LoginUseCase {
    override fun invoke(login: String, password: String): Flow<NetworkResponse<Profile>> {
        return authRepository.login(login, password)
    }
}