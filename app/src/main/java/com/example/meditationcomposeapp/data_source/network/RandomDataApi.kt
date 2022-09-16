package com.example.meditationcomposeapp.data_source.network

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomDataApi {

    @GET("/api/v2/beer/random_beer")
    suspend  fun getRandomBeer(): BeerResponse

    @GET("/api/v2/beers")
    suspend fun getBeerList(
        @Query("size") size: Int
    ): List<BeerResponse>
}