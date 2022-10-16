package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import javax.inject.Inject

class FermentationMapper @Inject constructor(
    private val tempMapper: Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse>,
) : Mapper<Method.Fermentation, BeerResponse.MethodResponse.FermentationResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse.FermentationResponse): Method.Fermentation {
        return Method.Fermentation(
            temp = tempMapper.mapFrom(objectFrom.temp!!)
        )
    }
}
