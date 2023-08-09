package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.Ingredients
import com.justparokq.core.model.beer_sorts.convertToMeasurementUnit
import com.justparokq.core.punk_source.api.model.web.BeerResponse
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