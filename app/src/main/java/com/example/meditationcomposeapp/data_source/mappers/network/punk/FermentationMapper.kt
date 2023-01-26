package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import javax.inject.Inject

class FermentationMapper @Inject constructor(
    private val tempMapper: Mapper<Method.Temp, com.example.punk_api.api.model.BeerResponse.MethodResponse.TempResponse>,
) : Mapper<Method.Fermentation, com.example.punk_api.api.model.BeerResponse.MethodResponse.FermentationResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.MethodResponse.FermentationResponse): Method.Fermentation {
        return Method.Fermentation(
            temp = if (objectFrom.temp?.value == null) tempMapper.mapFrom(objectFrom.temp!!) else null
        )
    }
}
