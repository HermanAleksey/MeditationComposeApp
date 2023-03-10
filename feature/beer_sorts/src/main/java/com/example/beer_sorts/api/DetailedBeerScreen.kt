package com.example.beer_sorts.api

import androidx.compose.runtime.Composable
import com.example.beer_sorts.internal.presentation.beer_details.InternalDetailedBeerScreen

@Composable
fun DetailedBeerScreen(
    beerId: Int,
    viewModel: DetailedBeerScreenViewModel,
) {
    InternalDetailedBeerScreen(beerId, viewModel)
}