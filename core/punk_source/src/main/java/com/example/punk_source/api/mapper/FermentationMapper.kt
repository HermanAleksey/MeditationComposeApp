package com.example.punk_source.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Method
import com.example.punk_source.api.model.BeerResponse
import javax.inject.Inject

class FermentationMapper @Inject constructor(
    private val tempMapper: Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse>,
) : Mapper<Method.Fermentation, BeerResponse.MethodResponse.FermentationResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse.FermentationResponse): Method.Fermentation {
        return Method.Fermentation(
            temp = if (objectFrom.temp?.value == null) tempMapper.mapFrom(objectFrom.temp!!) else null
        )
    }
}
