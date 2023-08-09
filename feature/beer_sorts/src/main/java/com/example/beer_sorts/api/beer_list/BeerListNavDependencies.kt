package com.example.beer_sorts.api.beer_list

import com.justparokq.core.common.navigation.NavDependencies
import com.justparokq.core.common.navigation.NavRoute

class BeerListNavDependencies(
    val navigateToBeerDetails: (beerId: Int) -> Unit,
    val navigateToNoInternetScreen: () -> Unit,
) : NavDependencies

sealed class BeerListNavRoute : NavRoute<BeerListNavDependencies>() {

    data class DetailedBeerScreen(private val beerId: Int) : BeerListNavRoute() {
        override fun navigate(navDependencies: BeerListNavDependencies) {
            navDependencies.navigateToBeerDetails(beerId)
        }
    }

    object NoInternetScreen : BeerListNavRoute() {
        override fun navigate(navDependencies: BeerListNavDependencies) {
            navDependencies.navigateToNoInternetScreen()
        }
    }
}
