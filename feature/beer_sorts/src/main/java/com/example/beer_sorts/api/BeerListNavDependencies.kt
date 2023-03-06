package com.example.beer_sorts.api

import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute

class BeerListNavDependencies(
    val navigateToBeerDetails: (beerId: Int) -> Unit
) : NavDependencies

sealed class BeerListNavRoute : NavRoute<BeerListNavDependencies>() {

    data class DetailedBeerScreen(private val beerId: Int) : BeerListNavRoute() {
        override fun navigate(navDependencies: BeerListNavDependencies) {
            navDependencies.navigateToBeerDetails(beerId)
        }
    }
}
