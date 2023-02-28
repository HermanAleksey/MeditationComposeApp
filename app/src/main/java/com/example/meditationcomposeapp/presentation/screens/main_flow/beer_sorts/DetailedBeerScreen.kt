package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_sorts

import androidx.compose.runtime.Composable
import com.example.beer_sorts.internal.presentation.beer_details.DetailedBeerScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailedBeerScreen(
    beerId: Int,
    viewModel: DetailedBeerScreenViewModel,
) {
    com.example.beer_sorts.api.DetailedBeerScreen(
        beerId = beerId,
        viewModel = viewModel
    )
}