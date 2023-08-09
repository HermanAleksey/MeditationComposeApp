package com.justparokq.core.authentication_source.api.use_case

import com.justparokq.core.authentication_source.api.repository.AuthenticationRepository
import com.justparokq.core.model.NetworkResponse
import com.example.network.SuccessInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RequestPasswordRestorationUseCase {
    operator fun invoke(login: String): Flow<NetworkResponse<SuccessInfo>>
}

class RequestPasswordRestorationUseCaseImpl @Inject constructor(
    private val authRepository: AuthenticationRepository
) : RequestPasswordRestorationUseCase {
    override fun invoke(login: String): Flow<NetworkResponse<SuccessInfo>> {
        return authRepository.requestPasswordRestoration(login)
    }
}