package com.example.punk_source.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Ingredients
import com.example.punk_source.api.model.web.BeerResponse
import javax.inject.Inject

class IngredientsMapper @Inject constructor(
    private val maltMapper: Mapper<Ingredients.Malt, BeerResponse.IngredientsResponse.MaltResponse>,
    private val hopsMapper: Mapper<Ingredients.Hops, BeerResponse.IngredientsResponse.HopsResponse>,
) :
    Mapper<Ingredients, BeerResponse.IngredientsResponse> {
    override fun mapFrom(objectFrom: BeerResponse.IngredientsResponse): Ingredients {
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