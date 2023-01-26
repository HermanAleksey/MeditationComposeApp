package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import javax.inject.Inject

class MaltMapper @Inject constructor(
    private val amountMapper: Mapper<Ingredients.Amount, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.AmountResponse>,
) : Mapper<Ingredients.Malt, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.MaltResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.IngredientsResponse.MaltResponse): Ingredients.Malt {
        return Ingredients.Malt(
            name = objectFrom.name!!,
            amount = amountMapper.mapFrom(objectFrom.amount!!)
        )
    }
}