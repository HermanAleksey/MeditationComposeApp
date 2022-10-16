package com.example.meditationcomposeapp.data_source.mappers.punk

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.beer.*
import javax.inject.Inject

class BeerMapper @Inject constructor(
    private val volumeMapper: Mapper<Volume, BeerResponse.VolumeResponse>,
    private val boilVolumeMapper: Mapper<BoilVolume, BeerResponse.BoilVolumeResponse>,
    private val methodMapper: Mapper<Method, BeerResponse.MethodResponse>,
    private val ingredientsMapper: Mapper<Ingredients, BeerResponse.IngredientsResponse>,
) : Mapper<Beer, BeerResponse> {
    override fun mapFrom(objectFrom: BeerResponse): Beer {
        return Beer(
            id = objectFrom.id!!,
            name = objectFrom.name!!,
            tagline = objectFrom.tagline!!,
            firstBrewed = objectFrom.firstBrewed!!,
            description = objectFrom.description!!,
            imageUrl = objectFrom.imageUrl!!,
            abv = objectFrom.abv!!,
            ibu = objectFrom.ibu!!,
            targetFg = objectFrom.targetFg!!,
            targetOg = objectFrom.targetOg!!,
            ebc = objectFrom.ebc!!,
            srm = objectFrom.srm!!,
            ph = objectFrom.ph!!,
            attenuationLevel = objectFrom.attenuationLevel!!,
            volume = volumeMapper.mapFrom(objectFrom.volume!!),
            boilVolume = boilVolumeMapper.mapFrom(objectFrom.boilVolume!!),
            method = methodMapper.mapFrom(objectFrom.method!!),
            ingredients = ingredientsMapper.mapFrom(objectFrom.ingredients!!),
            foodPairing = objectFrom.foodPairing,
            brewersTips = objectFrom.brewersTips!!,
            contributedBy = objectFrom.contributedBy!!
        )
    }
}