package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import com.example.meditationcomposeapp.model.entity.beer.convertToMeasurementUnit
import javax.inject.Inject

class TempMapper @Inject constructor() :
    Mapper<Method.Temp, com.example.punk_api.api.model.BeerResponse.MethodResponse.TempResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.MethodResponse.TempResponse): Method.Temp {
        return Method.Temp(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit(),
        )
    }
}
