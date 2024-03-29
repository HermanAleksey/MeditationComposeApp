package com.justparokq.core.punk_source.api.mapper

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.model.beer_sorts.*
import com.justparokq.core.punk_source.api.model.web.BeerResponse
import javax.inject.Inject

class BeerMapper @Inject constructor(
    private val volumeMapper: Mapper<Volume, BeerResponse.VolumeResponse>,
    private val boilVolumeMapper: Mapper<BoilVolume, BeerResponse.BoilVolumeResponse>,
    private val methodMapper: Mapper<Method, BeerResponse.MethodResponse>,
    private val ingredientsMapper: Mapper<Ingredients, BeerResponse.IngredientsResponse>,
) : Mapper<Beer, BeerResponse> {
    override fun mapFrom(objectFrom: BeerResponse): Beer {
        return Beer(
            id = objectFrom.id,
            name = objectFrom.name,
            tagline = objectFrom.tagline,
            firstBrewed = objectFrom.firstBrewed,
            description = objectFrom.description,
            imageUrl = objectFrom.imageUrl,
            abv = objectFrom.abv,
            ibu = objectFrom.ibu,
            targetFg = objectFrom.targetFg,
            targetOg = objectFrom.targetOg,
            ebc = objectFrom.ebc,
            srm = objectFrom.srm,
            ph = objectFrom.ph,
            attenuationLevel = objectFrom.attenuationLevel,
            volume = volumeMapper.mapFrom(objectFrom.volume),
            boilVolume = boilVolumeMapper.mapFrom(objectFrom.boilVolume),
            method = methodMapper.mapFrom(objectFrom.method),
            ingredients = ingredientsMapper.mapFrom(objectFrom.ingredients),
            foodPairing = objectFrom.foodPairing,
            brewersTips = objectFrom.brewersTips,
            contributedBy = objectFrom.contributedBy
        )
    }
}