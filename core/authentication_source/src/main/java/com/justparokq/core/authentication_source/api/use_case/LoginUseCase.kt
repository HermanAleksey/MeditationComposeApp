package com.justparokq.core.authentication_source.api.use_case

import com.justparokq.core.authentication_source.api.repository.AuthenticationRepository
import com.example.core.model.NetworkResponse
import com.example.core.model.authentication.Profile
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