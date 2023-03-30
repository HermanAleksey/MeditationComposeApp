package com.example.beer_sorts.api.beer_list

import androidx.compose.runtime.Composable
import com.example.beer_sorts.internal.presentation.beer_list.InternalBeerListScreen

@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel
) {
    InternalBeerListScreen(viewModel)
}