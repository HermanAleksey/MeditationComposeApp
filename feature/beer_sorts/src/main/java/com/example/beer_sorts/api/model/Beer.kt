package com.example.beer_sorts.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beer(
    var id: Int,
    var name: String,
    var tagline: String,
    var firstBrewed: String,
    var description: String,
    var imageUrl: String?,
    var abv: Double,
    var ibu: Double?,
    var targetFg: Int?,
    var targetOg: Double?,
    var ebc: Double?,
    var srm: Double?,
    var ph: Double?,
    var attenuationLevel: Double?,
    var volume: Volume?,
    var boilVolume: BoilVolume?,
    var method: Method?,
    var ingredients: Ingredients?,
    var foodPairing: List<String>,
    var brewersTips: String,
    var contributedBy: String,
) : Parcelable