package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.beer.Beer
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
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
        Column(modifier = Modifier.fillMaxSize()) {
            Toolbar()
            BeerList(beersPaging)
        }
    }
}

@Composable
fun BeerList(beers: LazyPagingItems<Beer>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Text(
                text = "Scroll for more recipes!",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        when (val state = beers.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
        when (val state = beers.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
        items(items = beers, key = { it.id }) { item ->
            item?.let {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)) {
                    Text(text = "${item.id} + ${item.abv}")
                }
            }
        }

        when (val state = beers.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                Loading()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
        }
    }
}

private fun LazyListScope.Loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

private fun LazyListScope.Error(
    message: String,
) {
    item {
        Text(
            text = message,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.error
        )
    }
}