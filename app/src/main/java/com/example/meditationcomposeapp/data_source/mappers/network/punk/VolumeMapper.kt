package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.meditationcomposeapp.data_source.entity.network.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.MeasurementUnit
import com.example.meditationcomposeapp.model.entity.beer.Volume
import com.example.meditationcomposeapp.model.entity.beer.convertToMeasurementUnit
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
