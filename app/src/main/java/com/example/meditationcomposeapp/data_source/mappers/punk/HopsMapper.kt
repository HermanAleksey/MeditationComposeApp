package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import javax.inject.Inject

class HopsMapper @Inject constructor(
    private val amountMapper: Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse>,
) : Mapper<Ingredients.Hops, BeerResponse.IngredientsResponse.HopsResponse> {
    override fun mapFrom(objectFrom: BeerResponse.IngredientsResponse.HopsResponse): Ingredients.Hops {
        return Ingredients.Hops(
            name = objectFrom.name!!,
            amount = amountMapper.mapFrom(objectFrom.amount!!),
            add = objectFrom.add!!,
            attribute = objectFrom.attribute!!,
        )
    }
}