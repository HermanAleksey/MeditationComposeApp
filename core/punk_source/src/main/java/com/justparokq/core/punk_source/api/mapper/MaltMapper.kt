package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.Ingredients
import com.justparokq.core.punk_source.api.model.web.BeerResponse
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