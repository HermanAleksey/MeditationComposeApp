package com.example.meditationcomposeapp.data_source.mappers.random_api

import com.example.meditationcomposeapp.data_source.entity.BeerResponse
import com.example.meditationcomposeapp.data_source.mappers.Mapper
import com.example.meditationcomposeapp.model.entity.Beer
import javax.inject.Inject

class BeerMapper @Inject constructor() : Mapper<Beer, BeerResponse> {
    override fun mapFrom(objectFrom: BeerResponse): Beer {
        return Beer(
            id = objectFrom.id,
            uid = objectFrom.uid,
            brand = objectFrom.brand,
            name = objectFrom.name,
            style = objectFrom.style,
            hop = objectFrom.hop,
            yeast = objectFrom.yeast,
            malts = objectFrom.malts,
            ibu = objectFrom.ibu,
            alcohol = objectFrom.alcohol,
            blg = objectFrom.blg
        )
    }
}