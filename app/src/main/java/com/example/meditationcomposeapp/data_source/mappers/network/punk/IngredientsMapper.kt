package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import javax.inject.Inject

class IngredientsMapper @Inject constructor(
    private val maltMapper: Mapper<Ingredients.Malt, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.MaltResponse>,
    private val hopsMapper: Mapper<Ingredients.Hops, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.HopsResponse>,
) :
    Mapper<Ingredients, com.example.punk_api.api.model.BeerResponse.IngredientsResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.IngredientsResponse): Ingredients {
        return Ingredients(
            malt = objectFrom.malt.map {
                maltMapper.mapFrom(it)
            },
            hops = objectFrom.hops.map {
                hopsMapper.mapFrom(it)
            },
            yeast = objectFrom.yeast
        )
    }
}
