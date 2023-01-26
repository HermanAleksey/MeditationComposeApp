package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Volume
import com.example.meditationcomposeapp.model.entity.beer.convertToMeasurementUnit
import javax.inject.Inject

class VolumeMapper @Inject constructor() :
    Mapper<Volume, com.example.punk_api.api.model.BeerResponse.VolumeResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.VolumeResponse): Volume {
        return Volume(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit(),
        )
    }
}
