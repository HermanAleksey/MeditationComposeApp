package com.justparokq.feature.beer_sorts.api.beer_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import com.justparokq.feature.beer_sorts.internal.presentation.beer_list.InternalBeerListScreen
import com.justparokq.core.common.utils.isInternetAvailable

@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
) {
    val beersPaging = viewModel.beersPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current


    InternalBeerListScreen(
        beersPaging = beersPaging,
        onBeerItemClicked = { beerId ->
            viewModel.onBeerItemClicked(
                beerId = beerId,
                isInternetAvailable = isInternetAvailable(context)
            )
        }
    )
}