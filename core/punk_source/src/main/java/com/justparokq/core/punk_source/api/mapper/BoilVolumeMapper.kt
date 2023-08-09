package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.BoilVolume
import com.justparokq.core.model.beer_sorts.convertToMeasurementUnit
import com.justparokq.core.punk_source.api.model.web.BeerResponse
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
