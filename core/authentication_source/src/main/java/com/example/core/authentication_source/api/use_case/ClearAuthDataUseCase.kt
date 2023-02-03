package com.example.core.authentication_source.api.use_case

import com.example.core.data_store.UserDataStore
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