package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.database.model.BeerListItem
import com.justparokq.core.model.beer_sorts.Beer
import javax.inject.Inject

class BeerDBMapper @Inject constructor() : Mapper<BeerListItem, Beer> {

    override fun mapFrom(objectFrom: Beer) = with(objectFrom) {
        BeerListItem(
            id = id,
            name = name,
            tagline = tagline,
            firstBrewed = firstBrewed,
            description = description,
            imageUrl = imageUrl ?: "",
            abv = abv,
            ibu = ibu,
            targetFg = targetFg,
            targetOg = targetOg,
            ebc = ebc,
            srm = srm,
            ph = ph,
            attenuationLevel = attenuationLevel,
            brewersTips = brewersTips,
            contributedBy = contributedBy,
        )
    }
}