package com.example.beer_sorts.api.beer_list

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.beer_sorts.internal.presentation.beer_list.InternalBeerListScreen

@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel
) {
    val beersPaging = viewModel.beersPagingFlow.collectAsLazyPagingItems()

    InternalBeerListScreen(
        beersPaging = beersPaging,
        onBeerItemClicked = viewModel::onBeerItemClicked
    )
}