package com.example.meditationcomposeapp.data_source.mappers.db

import com.example.meditationcomposeapp.data_source.entity.db.BeerDB
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.Beer
import javax.inject.Inject

class BeerMapper @Inject constructor() : Mapper<BeerDB, Beer> {

    override fun mapFrom(objectFrom: Beer) = with(objectFrom) {
        BeerDB(
            id = id,
            name = name,
            tagline = tagline,
            firstBrewed = firstBrewed,
            description = description,
            imageUrl = imageUrl,
            abv = abv,
            ibu = ibu,
            targetFg = targetFg,
            targetOg = targetOg,
            ebc = ebc,
            srm = srm,
            ph = ph,
            attenuationLevel = attenuationLevel,
            volumeValue = volume.value,
            volumeUnit = volume.unit.name,
            boilVolumeValue = boilVolume.value,
            boilVolumeUnit = boilVolume.unit.name,
            brewersTips = brewersTips,
            contributedBy = contributedBy,
        )
    }
}