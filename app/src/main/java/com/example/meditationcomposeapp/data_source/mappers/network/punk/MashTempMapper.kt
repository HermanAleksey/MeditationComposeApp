package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.meditationcomposeapp.data_source.entity.network.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import javax.inject.Inject

class MashTempMapper @Inject constructor(
    private val tempMapper: Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse>,
) : Mapper<Method.MashTemp, BeerResponse.MethodResponse.MashTempResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse.MashTempResponse): Method.MashTemp {
        return Method.MashTemp(
            temp = if (objectFrom.temp?.value == null) tempMapper.mapFrom(objectFrom.temp!!) else null,
            duration = objectFrom.duration
        )
    }
}