package com.example.beer_sorts.internal.presentation.beer_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.beer_sorts.api.DetailedBeerScreenViewModel
import com.example.beer_sorts.internal.presentation.beer_details.composables.BeerDescriptionCard
import com.example.design_system.common_composables.ColorBackground
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
internal fun InternalDetailedBeerScreen(
    beerId: Int,
    viewModel: DetailedBeerScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.onScreenOpened(beerId)
    }

    ColorBackground(
        isLoading = uiState.value.isLoading || uiState.value.beer == null,
        color = MaterialTheme.colors.background,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            uiState.value.beer?.let { beer ->
                if (!beer.imageUrl.isNullOrBlank())
                    GlideImage(
                        imageModel = { beer.imageUrl }, // loading a network image using an URL.
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.FillHeight,
                            alignment = Alignment.Center
                        ),
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(vertical = 15.dp)
                    )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (!beer.imageUrl.isNullOrBlank())
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(230.dp)
                            )
                        }

                    item {
                        BeerDescriptionCard(beer = beer)
                    }
                }
            }
        }
    }
}