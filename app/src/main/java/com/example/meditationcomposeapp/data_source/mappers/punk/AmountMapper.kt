package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import com.example.meditationcomposeapp.model.entity.beer.MeasurementUnit
import javax.inject.Inject

class AmountMapper @Inject constructor() :
    Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse> {
    override fun mapFrom(objectFrom: BeerResponse.IngredientsResponse.AmountResponse): Ingredients.Amount {
        return Ingredients.Amount(
            value = objectFrom.value,
            unit = MeasurementUnit.valueOf(objectFrom.unit.uppercase())
        )
    }
}