package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Method
import com.example.punk_api.api.model.BeerResponse
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