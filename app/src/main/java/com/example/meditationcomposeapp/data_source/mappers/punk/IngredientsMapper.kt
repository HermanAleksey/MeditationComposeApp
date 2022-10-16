package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
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
            yeast = objectFrom.yeast!!
        )
    }
}
