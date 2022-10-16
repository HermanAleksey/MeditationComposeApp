package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Method
import javax.inject.Inject

class MethodMapper @Inject constructor(
    private val mashTempMapper: Mapper<Method.MashTemp, BeerResponse.MethodResponse.MashTempResponse>,
    private val fermentationMapper: Mapper<Method.Fermentation, BeerResponse.MethodResponse.FermentationResponse>,
) :
    Mapper<Method, BeerResponse.MethodResponse> {
    override fun mapFrom(objectFrom: BeerResponse.MethodResponse): Method {
        return Method(
            mashTemp = objectFrom.mashTemp.map {
                mashTempMapper.mapFrom(it)
            },
            fermentation = fermentationMapper.mapFrom(objectFrom.fermentation!!),
            twist = objectFrom.twist!!
        )
    }
}
