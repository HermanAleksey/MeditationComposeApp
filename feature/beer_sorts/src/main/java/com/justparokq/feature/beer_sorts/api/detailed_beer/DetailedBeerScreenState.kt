package com.justparokq.feature.beer_sorts.api.detailed_beer

import androidx.compose.runtime.Stable
import com.justparokq.core.model.beer_sorts.Beer


@Stable
data class DetailedBeerScreenState(
    val isLoading: Boolean = false,
    val beer: Beer? = null,
)