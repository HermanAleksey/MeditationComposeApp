package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.Volume
import com.justparokq.core.model.beer_sorts.convertToMeasurementUnit
import com.justparokq.core.punk_source.api.model.web.BeerResponse
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
