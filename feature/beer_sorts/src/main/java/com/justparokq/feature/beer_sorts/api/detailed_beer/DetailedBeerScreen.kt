package com.justparokq.feature.beer_sorts.api.detailed_beer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.justparokq.feature.beer_sorts.internal.presentation.beer_details.InternalDetailedBeerScreen

@Composable
fun DetailedBeerScreen(
    beerId: Int,
    viewModel: DetailedBeerScreenViewModel,
) {
    InternalDetailedBeerScreen(
        uiState = viewModel.uiState.collectAsState(),
        onScreenOpened = { viewModel.onScreenOpened(beerId) }
    )
}