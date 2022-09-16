package com.example.meditationcomposeapp.data_source.network

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import retrofit2.http.Field
import retrofit2.http.GET

interface RandomDataApi {

    @GET("/api/v2/beer/random_beer")
    fun getRandomBeer(): BeerResponse

    @GET("/api/v2/beer/random_beer")
    fun getBeerList(@Field("size") size: Int): List<BeerResponse>
}