package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import javax.inject.Inject

class HopsMapper @Inject constructor(
    private val amountMapper: Mapper<Ingredients.Amount, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.AmountResponse>,
) : Mapper<Ingredients.Hops, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.HopsResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.IngredientsResponse.HopsResponse): Ingredients.Hops {
        return Ingredients.Hops(
            name = objectFrom.name!!,
            amount = amountMapper.mapFrom(objectFrom.amount!!),
            add = objectFrom.add!!,
            attribute = objectFrom.attribute!!,
        )
    }
}