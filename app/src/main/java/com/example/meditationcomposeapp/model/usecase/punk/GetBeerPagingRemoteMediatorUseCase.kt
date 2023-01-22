package com.example.meditationcomposeapp.model.usecase.punk

import com.example.meditationcomposeapp.model.usecase.ExecuteDatabaseTransactionUseCase
import com.example.meditationcomposeapp.model.usecase.punk.db.ClearBeersDBUseCase
import com.example.meditationcomposeapp.model.usecase.punk.db.InsertBeersIntoDBUseCase
import com.example.meditationcomposeapp.model.usecase.punk.network.GetBeersUseCase
import com.example.meditationcomposeapp.model.usecase.remote_keys.ClearRemoteKeysUseCase
import com.example.meditationcomposeapp.model.usecase.remote_keys.GetCreationTimeUseCase
import com.example.meditationcomposeapp.model.usecase.remote_keys.GetRemoteKeyBeBeerIdUseCase
import com.example.meditationcomposeapp.model.usecase.remote_keys.InsertAllRemoteKeysUseCase
import javax.inject.Inject

interface GetBeerPagingRemoteMediatorUseCase {

    operator fun invoke(): BeerPagingRemoteMediator
}

class GetBeerPagingRemoteMediatorUseCaseImpl @Inject constructor(
    private val insertBeersIntoDBUseCase: InsertBeersIntoDBUseCase,
    private val clearBeersDBUseCase: ClearBeersDBUseCase,
    private val getCreationTimeUseCase: GetCreationTimeUseCase,
    private val clearRemoteKeysUseCase: ClearRemoteKeysUseCase,
    private val getRemoteKeyBeBeerIdUseCase: GetRemoteKeyBeBeerIdUseCase,
    private val insertAllRemoteKeysUseCase: InsertAllRemoteKeysUseCase,
    private val getBeersUseCase: GetBeersUseCase,
    private val executeDatabaseTransaction: ExecuteDatabaseTransactionUseCase,
) : GetBeerPagingRemoteMediatorUseCase {

    override fun invoke(): BeerPagingRemoteMediator = BeerPagingRemoteMediator(
        insertBeersIntoDBUseCase = insertBeersIntoDBUseCase,
        clearBeersDBUseCase = clearBeersDBUseCase,
        getCreationTimeUseCase = getCreationTimeUseCase,
        clearRemoteKeysUseCase = clearRemoteKeysUseCase,
        getRemoteKeyBeBeerIdUseCase = getRemoteKeyBeBeerIdUseCase,
        insertAllRemoteKeysUseCase = insertAllRemoteKeysUseCase,
        getBeersUseCase = getBeersUseCase,
        executeDatabaseTransaction = executeDatabaseTransaction,
    )
}