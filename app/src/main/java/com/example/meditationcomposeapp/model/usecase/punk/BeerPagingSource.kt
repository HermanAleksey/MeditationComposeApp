package com.example.meditationcomposeapp.model.usecase.punk

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meditationcomposeapp.model.entity.beer.Beer


class BeerPagingSource(
    private val getBeersUseCase: GetBeersFromDBUseCase,
) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> =
        try {
            val page = params.key ?: 1
            val size = params.loadSize
            val data = getBeersUseCase(offset = size * (page - 1), limit = size)
            Log.e("TAGG", "BeerPagingSource offset:${size * (page - 1)}  limit:$size page:$page")
            Log.e("TAGG", "BeerPagingSource load: $data")

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1,
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}