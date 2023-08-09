package com.justparokq.core.authentication_source.api.use_case

import com.justparokq.core.authentication_source.api.repository.AuthenticationRepository
import com.justparokq.core.model.NetworkResponse
import com.example.network.SuccessInfo
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