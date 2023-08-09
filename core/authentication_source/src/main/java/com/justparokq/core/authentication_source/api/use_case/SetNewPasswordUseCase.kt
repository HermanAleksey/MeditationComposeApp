package com.justparokq.core.authentication_source.api.use_case

import com.justparokq.core.authentication_source.api.repository.AuthenticationRepository
import com.example.core.model.NetworkResponse
import com.example.network.SuccessInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SetNewPasswordUseCase {
    operator fun invoke(login: String, newPassword: String): Flow<NetworkResponse<SuccessInfo>>
}

class SetNewPasswordUseCaseImpl @Inject constructor(
    private val authRepository: AuthenticationRepository
) : SetNewPasswordUseCase {
    override fun invoke(login: String, newPassword: String): Flow<NetworkResponse<SuccessInfo>> {
        return authRepository.setNewPassword(login, newPassword)
    }
}