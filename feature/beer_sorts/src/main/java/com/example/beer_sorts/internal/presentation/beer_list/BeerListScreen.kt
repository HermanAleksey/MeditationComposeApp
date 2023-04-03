package com.example.beer_sorts.internal.presentation.beer_list

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.beer_sorts.internal.presentation.beer_list.composables.BeerItem
import com.example.common.utils.emptyString
import com.example.database.model.BeerListItem
import com.example.design_system.AppTheme
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.beer_sorts.R
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun InternalBeerListScreen(
    beersPaging: LazyPagingItems<BeerListItem>,
    onBeerItemClicked: (id: Int) -> Unit,
) {
    ColorBackground(
        lockScreenWhenLoading = true,
        color = MaterialTheme.colors.background
    ) {
        BeerList(
            beersPaging,
            onBeerItemClicked = {
                onBeerItemClicked(it)
            }
        )
    }
}

@Composable
internal fun BeerList(
    beers: LazyPagingItems<BeerListItem>,
    onBeerItemClicked: (id: Int) -> Unit,
) {
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
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        when (val state = beers.loadState.prepend) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                LoadingState()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: emptyString())
            }
            else -> {}
        }
        when (val state = beers.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                LoadingState()
            }
            is LoadState.Error -> {
                Error(message = state.error.message ?: emptyString())
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

@Preview
@Composable
fun InternalBeerListScreenPreview() {
    AppTheme {
        InternalBeerListScreen(
            beersPaging = flowOf(
                PagingData.from(
                    listOf(
                        BeerListItem(
                            id = 1,
                            name = "Sapporo Premium",
                            tagline = "Birra Moretti",
                            firstBrewed = "10.10.2010",
                            description = "Beers can range from having mild flavor to an intense bold taste experience. A mild beer might taste light, delicate, crisp and refreshing. A bolder beer might have stronger flavors that result in a sharper taste which might be described as intense, powerful, robust and hearty",
                            imageUrl = "data:image/webp;base64,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",
                            abv = 10.2,
                            ibu = 13.0,
                            targetFg = 32,
                            targetOg = 29.3,
                            ebc = 23.0,
                            srm = 32.0,
                            ph = 11.2,
                            attenuationLevel = 99.9,
                            brewersTips = "The earthy and floral aromas from the hops can be overpowering. Drop a little Cascade in at the end of the boil to lift the profile with a bit of citrus.",
                            contributedBy = "Andre Puanijy",
                        )
                    )
                )
            ).collectAsLazyPagingItems(),
            onBeerItemClicked = {}
        )
    }
}