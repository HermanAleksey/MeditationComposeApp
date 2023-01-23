package com.example.meditationcomposeapp.model.usecase

import androidx.room.withTransaction
import com.example.meditationcomposeapp.data_source.database.AppDatabase
import javax.inject.Inject

interface ExecuteDatabaseTransactionUseCase {

    suspend operator fun invoke(block: suspend () -> Unit)
}

class ExecuteDatabaseTransactionUseCaseImpl @Inject constructor(
    private val database: AppDatabase,
) : ExecuteDatabaseTransactionUseCase {

    override suspend fun invoke(block: suspend () -> Unit) {
        database.withTransaction {
            block()
        }
    }
}