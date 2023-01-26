package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Volume
import com.example.core.model.beer_sorts.convertToMeasurementUnit
import com.example.punk_api.api.model.BeerResponse
import javax.inject.Inject

class VolumeMapper @Inject constructor() :
    Mapper<Volume, BeerResponse.VolumeResponse> {
    override fun mapFrom(objectFrom: BeerResponse.VolumeResponse): Volume {
        return Volume(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit(),
        )
    }
}
