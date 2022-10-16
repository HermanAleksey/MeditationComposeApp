package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Ingredients
import com.example.meditationcomposeapp.model.entity.beer.Method
import javax.inject.Inject

class TempMapper @Inject constructor() :
    Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse.TempResponse): Method.Temp {
        return Method.Temp(
            value = objectFrom.value!!,
            unit = objectFrom.unit!!
        )
    }
}
