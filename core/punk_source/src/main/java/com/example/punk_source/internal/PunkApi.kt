package com.example.punk_source.internal

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Beer
import com.example.punk_source.api.mapper.BeerDBMapper
import com.example.punk_source.api.model.db.BeerListItem
import com.example.punk_source.api.model.web.BeerResponse
import dagger.Binds
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://punkapi.com/documentation/v2
interface PunkApi {

    //TODO why v2 dont count from base URL?
    @GET("/v2/beers/{id}")
    suspend fun getBeerById(
        @Path("id") page: Int,
    ): List<BeerResponse>

    @GET("/v2/beers/random")
    suspend fun getRandomBeer(): BeerResponse

    @GET("/v2/beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): List<BeerResponse>
}