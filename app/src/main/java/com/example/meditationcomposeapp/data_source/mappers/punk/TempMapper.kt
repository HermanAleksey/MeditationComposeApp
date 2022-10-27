package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import com.example.meditationcomposeapp.model.entity.beer.MeasurementUnit
import javax.inject.Inject

class TempMapper @Inject constructor() :
    Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse.TempResponse): Method.Temp {
        return Method.Temp(
            value = objectFrom.value,
            unit = MeasurementUnit.valueOf(objectFrom.unit.uppercase())
        )
    }
}