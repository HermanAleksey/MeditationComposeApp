package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import com.example.meditationcomposeapp.model.entity.beer.convertToMeasurementUnit
import javax.inject.Inject

class AmountMapper @Inject constructor() :
    Mapper<Ingredients.Amount, com.example.punk_api.api.model.BeerResponse.IngredientsResponse.AmountResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.IngredientsResponse.AmountResponse): Ingredients.Amount {
        return Ingredients.Amount(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit()
        )
    }
}