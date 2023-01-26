package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Ingredients
import com.example.core.model.beer_sorts.convertToMeasurementUnit
import com.example.punk_api.api.model.BeerResponse
import javax.inject.Inject

class AmountMapper @Inject constructor() :
    Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse> {
    override fun mapFrom(objectFrom: BeerResponse.IngredientsResponse.AmountResponse): Ingredients.Amount {
        return Ingredients.Amount(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit()
        )
    }
}