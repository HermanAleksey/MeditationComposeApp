package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.BoilVolume
import com.example.meditationcomposeapp.model.entity.beer.convertToMeasurementUnit
import javax.inject.Inject

class BoilVolumeMapper @Inject constructor() :
    Mapper<BoilVolume, com.example.punk_api.api.model.BeerResponse.BoilVolumeResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse.BoilVolumeResponse): BoilVolume {
        return BoilVolume(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit(),
        )
    }
}
