package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.meditationcomposeapp.data_source.entity.network.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import com.example.meditationcomposeapp.model.entity.beer.MeasurementUnit
import com.example.meditationcomposeapp.model.entity.beer.convertToMeasurementUnit
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
