package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Ingredients
import com.example.punk_api.api.model.BeerResponse
import javax.inject.Inject

class MaltMapper @Inject constructor(
    private val amountMapper: Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse>,
) : Mapper<Ingredients.Malt, BeerResponse.IngredientsResponse.MaltResponse> {
    override fun mapFrom(objectFrom: BeerResponse.IngredientsResponse.MaltResponse): Ingredients.Malt {
        return Ingredients.Malt(
            name = objectFrom.name!!,
            amount = amountMapper.mapFrom(objectFrom.amount!!)
        )
    }
}