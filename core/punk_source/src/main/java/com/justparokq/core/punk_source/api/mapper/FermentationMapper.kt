package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.Method
import com.justparokq.core.punk_source.api.model.web.BeerResponse
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
