package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Unit
import com.example.meditationcomposeapp.model.entity.beer.Volume
import javax.inject.Inject

class VolumeMapper @Inject constructor() :
    Mapper<Volume, BeerResponse.VolumeResponse> {
    override fun mapFrom(objectFrom: BeerResponse.VolumeResponse): Volume {
        return Volume(
            value = objectFrom.value,
            unit = Unit.valueOf(objectFrom.unit.uppercase())
        )
    }
}
