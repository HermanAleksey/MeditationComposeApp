package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_sorts

import androidx.compose.runtime.Composable
import com.example.beer_sorts.api.detailed_beer.DetailedBeerScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailedBeerScreen(
    beerId: Int,
    viewModel: DetailedBeerScreenViewModel,
) {
    com.example.beer_sorts.api.detailed_beer.DetailedBeerScreen(
        beerId = beerId,
        viewModel = viewModel
    )
}
