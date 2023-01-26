package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Method
import com.example.punk_api.api.model.BeerResponse
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
            twist = objectFrom.twist
        )
    }
}
