package com.example.meditationcomposeapp.model.usecase.punk

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.meditationcomposeapp.model.entity.beer.Beer
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class BeerPagingRemoteMediator(
    private val pageSize: Int,

    private val insertBeersIntoDBUseCase: InsertBeersIntoDBUseCase,
    private val clearBeersDBUseCase: ClearBeersDBUseCase,
    private val getBeersUseCase: GetBeersUseCase,
) : RemoteMediator<Int, Beer>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (true/*System.currentTimeMillis() - db.lastUpdated() <= cacheTimeout*/) {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Beer>
    ): MediatorResult {
        return try {
            val nextPageToLoad = when (loadType) {
                LoadType.REFRESH -> {
                    Log.e("TAGG", "load: REFRESH")
                    null
                }
                LoadType.PREPEND -> {
                    Log.e("TAGG", "load: PREPEND :${state.pages.last().nextKey}")
//                    state.pages.last().nextKey
                    return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
                }
                LoadType.APPEND -> {
                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.
                    state.pages.last().prevKey
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    Log.e("TAGG", "load: APPEND :${state.pages.last().toString()}")
                    state.pages.last().prevKey?.plus(1)
                }
            }

            val beersList = getBeersUseCase(
                page = nextPageToLoad ?: 1,
                pageSize = pageSize,
            )
            Log.e("TAGG", "BeerMediator response: $beersList")
            Log.e("TAGG", "BeerMediator loadKey: $nextPageToLoad")

            if (loadType == LoadType.REFRESH) {
                clearBeersDBUseCase()
            }
            insertBeersIntoDBUseCase(beersList)

            MediatorResult.Success(
                endOfPaginationReached = beersList.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}