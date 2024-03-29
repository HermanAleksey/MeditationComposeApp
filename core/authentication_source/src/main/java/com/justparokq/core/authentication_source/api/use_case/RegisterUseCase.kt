package com.justparokq.core.authentication_source.api.use_case

import com.justparokq.core.authentication_source.api.repository.AuthenticationRepository
import com.justparokq.core.model.NetworkResponse
import com.example.network.SuccessInfo
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