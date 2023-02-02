package com.example.beer_sorts.api

import androidx.compose.runtime.Composable
import com.example.beer_sorts.internal.presentation.beer_list.BeerListScreenViewModel
import com.example.beer_sorts.internal.presentation.beer_list.InternalBeerListScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
    navigator: DestinationsNavigator,
) {
    InternalBeerListScreen(viewModel, navigator)
}