package com.example.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.example.core.model.beer_sorts.Ingredients
import com.example.punk_source.api.model.web.BeerResponse
import javax.inject.Inject

class HopsMapper @Inject constructor(
    private val amountMapper: Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse>,
) : Mapper<Ingredients.Hops, BeerResponse.IngredientsResponse.HopsResponse> {
    override fun mapFrom(objectFrom: BeerResponse.IngredientsResponse.HopsResponse): Ingredients.Hops {
        return Ingredients.Hops(
            name = objectFrom.name!!,
            amount = amountMapper.mapFrom(objectFrom.amount!!),
            add = objectFrom.add.let {
                if (it.isNullOrBlank()) {
                    Ingredients.AddOrder.NA
                } else when (it.lowercase()) {
                    "start" -> Ingredients.AddOrder.START
                    "middle" -> Ingredients.AddOrder.MIDDLE
                    "end" -> Ingredients.AddOrder.END
                    else -> Ingredients.AddOrder.NA
                }
            },
            attribute = objectFrom.attribute!!,
        )
    }
}