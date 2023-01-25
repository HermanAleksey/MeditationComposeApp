package com.example.beer_sorts.internal.presentation.beer_details

import com.example.beer_sorts.api.model.Beer

data class DetailedBeerScreenState(
    val isLoading: Boolean = false,
    val beer: Beer? = null,
)