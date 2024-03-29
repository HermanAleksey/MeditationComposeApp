package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.Method
import com.justparokq.core.model.beer_sorts.convertToMeasurementUnit
import com.justparokq.core.punk_source.api.model.web.BeerResponse
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
