package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.meditationcomposeapp.data_source.entity.network.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.BoilVolume
import com.example.meditationcomposeapp.model.entity.beer.MeasurementUnit
import javax.inject.Inject

class BoilVolumeMapper @Inject constructor() :
    Mapper<BoilVolume, BeerResponse.BoilVolumeResponse> {
    override fun mapFrom(objectFrom: BeerResponse.BoilVolumeResponse): BoilVolume {
        return BoilVolume(
            value = objectFrom.value,
            unit = MeasurementUnit.valueOf(objectFrom.unit.uppercase()),
        )
    }
}
