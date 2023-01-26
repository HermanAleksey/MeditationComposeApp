package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RegisterUseCase {
    operator fun invoke(
        name: String,
        login: String,
        password: String
    ): Flow<NetworkResponse<SuccessInfo>>
}

class RegisterUseCaseImpl @Inject constructor(
    private val authRepository: AuthenticationRepository
) : RegisterUseCase {
    override fun invoke(
        name: String,
        login: String,
        password: String
    ): Flow<NetworkResponse<SuccessInfo>> {
        return authRepository.register(name, login, password)
    }
}