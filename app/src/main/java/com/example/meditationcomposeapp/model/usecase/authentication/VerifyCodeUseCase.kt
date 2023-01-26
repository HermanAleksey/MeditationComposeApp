package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface VerifyCodeUseCase {
    operator fun invoke(login: String, code: String): Flow<NetworkResponse<SuccessInfo>>
}

class VerifyCodeUseCaseImpl @Inject constructor(
    private val authRepository: AuthenticationRepository
) : VerifyCodeUseCase {
    override fun invoke(login: String, code: String): Flow<NetworkResponse<SuccessInfo>> {
        return authRepository.verifyCode(login, code)
    }
}