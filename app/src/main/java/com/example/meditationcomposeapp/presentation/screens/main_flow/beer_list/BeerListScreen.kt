package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.runtime.Composable
import com.example.beer_sorts.internal.presentation.beer_list.BeerListScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
    navigator: DestinationsNavigator,
) {
    com.example.beer_sorts.api.BeerListScreen(
        viewModel = viewModel,
        navigator = navigator
    )
}