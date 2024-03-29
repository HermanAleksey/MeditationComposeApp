package com.justparokq.core.data_store.user.use_case

import com.justparokq.core.common.utils.emptyString
import com.justparokq.core.data_store.user.UserDataStore
import javax.inject.Inject

interface ClearAuthDataUseCase {
    suspend operator fun invoke()
}

class ClearAuthDataUseCaseImpl @Inject constructor(
    private val userDataStore: UserDataStore
): ClearAuthDataUseCase {
    override suspend fun invoke() {
        userDataStore.writeLogin(emptyString())
        userDataStore.writePassword(emptyString())
    }
}