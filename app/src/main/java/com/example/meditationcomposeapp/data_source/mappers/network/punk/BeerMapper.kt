package com.example.meditationcomposeapp.data_source.mappers.network.punk

import com.example.punk_api.api.model.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.*
import javax.inject.Inject

class BeerMapper @Inject constructor(
    private val volumeMapper: Mapper<Volume, com.example.punk_api.api.model.BeerResponse.VolumeResponse>,
    private val boilVolumeMapper: Mapper<BoilVolume, com.example.punk_api.api.model.BeerResponse.BoilVolumeResponse>,
    private val methodMapper: Mapper<Method, com.example.punk_api.api.model.BeerResponse.MethodResponse>,
    private val ingredientsMapper: Mapper<Ingredients, com.example.punk_api.api.model.BeerResponse.IngredientsResponse>,
) : Mapper<Beer, com.example.punk_api.api.model.BeerResponse> {
    override fun mapFrom(objectFrom: com.example.punk_api.api.model.BeerResponse): Beer {
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