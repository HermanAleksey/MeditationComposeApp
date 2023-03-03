package com.example.beer_sorts.internal.presentation.beer_list

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.beer_sorts.api.BeerListNavDependencies
import com.example.beer_sorts.api.BeerListScreenViewModel
import com.example.beer_sorts.internal.presentation.beer_list.composables.BeerItem
import com.example.common.navigation.NavDependenciesProvider
import com.example.database.model.BeerListItem
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.beer_sorts.R

@Composable
internal fun InternalBeerListScreen(
    viewModel: BeerListScreenViewModel
) {
    val beersPaging = viewModel.beersPagingFlow.collectAsLazyPagingItems()

    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(BeerListNavDependencies::class.java)

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    ColorBackground(
        lockScreenWhenLoading = true,
        color = MaterialTheme.colors.background
    ) {
        BeerList(beersPaging, onBeerItemClicked = {
            viewModel.onBeerItemClicked(it)
        })
    }
}

@Composable
internal fun BeerList(beers: LazyPagingItems<BeerListItem>, onBeerItemClicked: (id: Int) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
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
            else -> {}
        }
        when (val state = beers.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                LoadingState()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: "")
            }
            else -> {}
        }
        items(items = beers, key = { it.id }) { item ->
            item?.let {
                BeerItem(beer = it, onClick = { onBeerItemClicked(it.id) })
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
            else -> {}
        }
    }
}

@Suppress("FunctionName")
private fun LazyListScope.LoadingState() {
    item {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.secondary
        )
    }
}

@Suppress("FunctionName")
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