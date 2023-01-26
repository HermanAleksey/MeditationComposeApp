package com.example.punk_api.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.BoilVolume
import com.example.core.model.beer_sorts.convertToMeasurementUnit
import com.example.punk_api.api.model.BeerResponse
import javax.inject.Inject

class BoilVolumeMapper @Inject constructor() :
    Mapper<BoilVolume, BeerResponse.BoilVolumeResponse> {
    override fun mapFrom(objectFrom: BeerResponse.BoilVolumeResponse): BoilVolume {
        return BoilVolume(
            value = objectFrom.value,
            unit = objectFrom.unit.convertToMeasurementUnit(),
        )
    }
}
