package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.runtime.Composable
import com.example.beer_sorts.internal.presentation.beer_list.BeerListScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
) {
    com.example.beer_sorts.api.BeerListScreen(
        viewModel = viewModel,
    )
}