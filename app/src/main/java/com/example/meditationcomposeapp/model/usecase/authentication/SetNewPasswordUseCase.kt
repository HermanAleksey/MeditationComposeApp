package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.SuccessInfo
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