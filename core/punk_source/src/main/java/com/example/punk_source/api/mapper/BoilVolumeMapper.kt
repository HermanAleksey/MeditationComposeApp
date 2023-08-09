package com.example.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.example.core.model.beer_sorts.BoilVolume
import com.example.core.model.beer_sorts.convertToMeasurementUnit
import com.example.punk_source.api.model.web.BeerResponse
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
