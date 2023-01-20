package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.meditationcomposeapp.data_source.entity.network.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
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