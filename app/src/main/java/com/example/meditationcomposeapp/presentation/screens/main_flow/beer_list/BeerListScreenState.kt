package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import com.example.meditationcomposeapp.model.entity.beer.Beer

data class BeerListScreenState(
    val isLoading: Boolean = false,
    val beerList: List<Beer> = listOf()
)