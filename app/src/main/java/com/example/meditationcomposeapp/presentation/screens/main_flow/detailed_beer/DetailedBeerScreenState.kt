package com.example.meditationcomposeapp.presentation.screens.main_flow.detailed_beer

import com.example.meditationcomposeapp.model.entity.beer.Beer

data class DetailedBeerScreenState(
    val isLoading: Boolean = false,
    val beer: Beer? = null,
)