package com.example.meditationcomposeapp.data_source.network

import com.example.meditationcomposeapp.data_source.entity.network.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://punkapi.com/documentation/v2
interface PunkApi {

    //TODO why v2 dont count from base URL?
    @GET("/v2/beers/{id}")
    suspend fun getBeerById(
        @Path("id") page: Int,
    ): BeerResponse

    @GET("/v2/beers/random")
    suspend fun getRandomBeer(): BeerResponse

    @GET("/v2/beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): List<BeerResponse>
}