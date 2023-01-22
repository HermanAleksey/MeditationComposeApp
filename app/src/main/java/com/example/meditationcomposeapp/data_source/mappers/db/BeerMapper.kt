package com.example.meditationcomposeapp.data_source.mappers.db

import com.example.meditationcomposeapp.data_source.entity.db.BeerListItem
import com.example.meditationcomposeapp.data_source.mappers.BidirectionalMapper
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Beer
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