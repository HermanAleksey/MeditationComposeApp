package com.example.punk_source.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Method
import com.example.core.model.beer_sorts.convertToMeasurementUnit
import com.example.punk_source.api.model.web.BeerResponse
import javax.inject.Inject

class TempMapper @Inject constructor() :
    Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse.TempResponse): Method.Temp {
        return Method.Temp(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit(),
        )
    }
}
