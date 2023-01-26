package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Ingredients
import com.example.punk_api.api.model.BeerResponse
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