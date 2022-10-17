package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.model.usecase.punk.GetBeersUseCase


class BeerPagingSource (
    private val getBeersUseCase: GetBeersUseCase,
) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> =
        try {
            val page = params.key ?: 1
            val size = params.loadSize
            val data = getBeersUseCase(page = page, pageSize = size)
//            if (params.placeholdersEnabled) {
//                val itemsAfter = data.count - from + data.results.size
//                LoadResult.Page(
//                    data = data.results,
//                    prevKey = if (page == 0) null else page - 1,
//                    nextKey = if (data.results.isEmpty()) null else page + 1,
//                    itemsAfter = if (itemsAfter > size) size else itemsAfter.toInt(),
//                    itemsBefore = from
//                )
//            } else {
//            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}