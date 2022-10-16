package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
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