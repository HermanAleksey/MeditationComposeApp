package com.example.meditationcomposeapp.model.usecase.authentication

import javax.inject.Inject

interface ClearAuthDataUseCase {
    suspend operator fun invoke()
}

class ClearAuthDataUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): ClearAuthDataUseCase {
    override suspend fun invoke() {
        userDataStore.writeLogin("")
        userDataStore.writePassword("")
    }
}