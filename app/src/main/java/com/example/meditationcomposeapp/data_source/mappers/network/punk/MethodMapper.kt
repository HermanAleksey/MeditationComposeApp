package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import javax.inject.Inject

class MethodMapper @Inject constructor(
    private val mashTempMapper: Mapper<Method.MashTemp, com.example.punk_api.api.model.BeerResponse.MethodResponse.MashTempResponse>,
    private val fermentationMapper: Mapper<Method.Fermentation, com.example.punk_api.api.model.BeerResponse.MethodResponse.FermentationResponse>,
) :
    Mapper<Method, com.example.punk_api.api.model.BeerResponse.MethodResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.MethodResponse): Method {
        return Method(
            mashTemp = objectFrom.mashTemp.map {
                mashTempMapper.mapFrom(it)
            },
            fermentation = fermentationMapper.mapFrom(objectFrom.fermentation!!),
            twist = objectFrom.twist
        )
    }
}
