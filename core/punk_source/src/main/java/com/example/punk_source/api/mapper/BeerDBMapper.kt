package com.example.punk_source.api.mapper

import com.example.common.mapper.Mapper
import com.example.core.model.beer_sorts.Beer
import com.example.database.model.BeerListItem
import javax.inject.Inject

class BeerDBMapper @Inject constructor() : Mapper<com.example.database.model.BeerListItem, Beer> {

    override fun mapFrom(objectFrom: Beer) = with(objectFrom) {
        com.example.database.model.BeerListItem(
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