package com.example.beer_sorts.internal.presentation.beer_details

import com.example.core.model.beer_sorts.Beer


data class DetailedBeerScreenState(
    val isLoading: Boolean = false,
    val beer: Beer? = null,
)