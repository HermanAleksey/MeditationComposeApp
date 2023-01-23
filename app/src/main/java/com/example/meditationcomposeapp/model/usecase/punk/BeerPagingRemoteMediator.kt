package com.example.meditationcomposeapp.model.usecase.punk

import androidx.paging.*
import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.data_source.entity.db.RemoteKeys
import com.example.meditationcomposeapp.model.usecase.ExecuteDatabaseTransactionUseCase
import com.example.meditationcomposeapp.model.usecase.punk.db.ClearBeersDBUseCase
import com.example.meditationcomposeapp.model.usecase.punk.db.InsertBeersIntoDBUseCase
import com.example.meditationcomposeapp.model.usecase.punk.network.GetBeersUseCase
import com.example.meditationcomposeapp.model.usecase.remote_keys.*
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class BeerPagingRemoteMediator(
    private val insertBeersIntoDBUseCase: InsertBeersIntoDBUseCase,
    private val clearBeersDBUseCase: ClearBeersDBUseCase,
    private val getCreationTimeUseCase: GetCreationTimeUseCase,
    private val clearRemoteKeysUseCase: ClearRemoteKeysUseCase,
    private val getRemoteKeyBeBeerIdUseCase: GetRemoteKeyBeBeerIdUseCase,
    private val insertAllRemoteKeysUseCase: InsertAllRemoteKeysUseCase,

    private val getBeersUseCase: GetBeersUseCase,
    private val executeDatabaseTransaction: ExecuteDatabaseTransactionUseCase,
) : RemoteMediator<Int, BeerListItem>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (System.currentTimeMillis() - (getCreationTimeUseCase() ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerListItem>
    ): MediatorResult {
        return try {
            val page: Int = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val beerList = getBeersUseCase(page = page, pageSize = state.config.pageSize)

            val endOfPaginationReached = beerList.isEmpty()

            executeDatabaseTransaction {
                if (loadType == LoadType.REFRESH) {
                    clearBeersDBUseCase()
                    clearRemoteKeysUseCase()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = beerList.map {
                    RemoteKeys(
                        beerId = it.id,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                insertAllRemoteKeysUseCase(remoteKeys)
                insertBeersIntoDBUseCase(beerList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, BeerListItem>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                getRemoteKeyBeBeerIdUseCase(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, BeerListItem>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { beer ->
            getRemoteKeyBeBeerIdUseCase(beer.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, BeerListItem>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { beer ->
            getRemoteKeyBeBeerIdUseCase(beer.id)
        }
    }
}