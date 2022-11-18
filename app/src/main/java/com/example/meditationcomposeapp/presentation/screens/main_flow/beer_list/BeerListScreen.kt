package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.screens.destinations.DetailedBeerScreenDestination
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable.BeerItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
    navigator: DestinationsNavigator,
) {
    val beersPaging = viewModel.beersPagingFlow.collectAsLazyPagingItems()

    ColorBackground(
        lockScreenWhenLoading = true,
        color = MaterialTheme.colors.background
    ) {
        BeerList(beersPaging, onBeerItemClicked = {
            viewModel.onBeerItemClicked {
                navigator.navigate(
                    DetailedBeerScreenDestination(
                        it
                    )
                )
            }
        })
    }
}

@Composable
fun BeerList(beers: LazyPagingItems<Beer>, onBeerItemClicked: (Beer) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
            .background(MaterialTheme.colors.background),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.beer_list_title),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        when (val state = beers.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                LoadingState()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
        when (val state = beers.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                LoadingState()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
        items(items = beers, key = { it.id }) { item ->
            item?.let {
                BeerItem(beer = it, onClick = { onBeerItemClicked(it) })
            }
        }

        when (val state = beers.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                LoadingState()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
    }
}

private fun LazyListScope.LoadingState() {
    item {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.secondary
        )
    }
}

private fun LazyListScope.Error(
    message: String,
) {
    item {
        Text(
            text = message,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.error
        )
    }
}