package com.example.meditationcomposeapp.model.usecase.authentication

import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.data_store.UserDataStoreImpl
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