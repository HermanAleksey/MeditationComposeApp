package com.justparokq.core.punk_source.api.use_case.punk

import com.justparokq.core.database.ExecuteDatabaseTransactionUseCase
import com.justparokq.core.punk_source.api.use_case.punk.db.ClearBeersDBUseCase
import com.justparokq.core.punk_source.api.use_case.punk.db.InsertBeersIntoDBUseCase
import com.justparokq.core.punk_source.api.use_case.punk.network.GetBeersUseCase
import com.justparokq.core.punk_source.api.use_case.remote_keys.ClearRemoteKeysUseCase
import com.justparokq.core.punk_source.api.use_case.remote_keys.GetCreationTimeUseCase
import com.justparokq.core.punk_source.api.use_case.remote_keys.GetRemoteKeyByBeerIdUseCase
import com.justparokq.core.punk_source.api.use_case.remote_keys.InsertAllRemoteKeysUseCase
import javax.inject.Inject

interface GetBeerPagingRemoteMediatorUseCase {

    operator fun invoke(): BeerPagingRemoteMediator
}

class GetBeerPagingRemoteMediatorUseCaseImpl @Inject constructor(
    private val insertBeersIntoDBUseCase: InsertBeersIntoDBUseCase,
    private val clearBeersDBUseCase: ClearBeersDBUseCase,
    private val getCreationTimeUseCase: GetCreationTimeUseCase,
    private val clearRemoteKeysUseCase: ClearRemoteKeysUseCase,
    private val getRemoteKeyByBeerIdUseCase: GetRemoteKeyByBeerIdUseCase,
    private val insertAllRemoteKeysUseCase: InsertAllRemoteKeysUseCase,
    private val getBeersUseCase: GetBeersUseCase,
    private val executeDatabaseTransaction: ExecuteDatabaseTransactionUseCase,
) : GetBeerPagingRemoteMediatorUseCase {

    override fun invoke(): BeerPagingRemoteMediator = BeerPagingRemoteMediator(
        insertBeersIntoDBUseCase = insertBeersIntoDBUseCase,
        clearBeersDBUseCase = clearBeersDBUseCase,
        getCreationTimeUseCase = getCreationTimeUseCase,
        clearRemoteKeysUseCase = clearRemoteKeysUseCase,
        getRemoteKeyByBeerIdUseCase = getRemoteKeyByBeerIdUseCase,
        insertAllRemoteKeysUseCase = insertAllRemoteKeysUseCase,
        getBeersUseCase = getBeersUseCase,
        executeDatabaseTransaction = executeDatabaseTransaction,
    )
}