package com.example.beer_sorts.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredients(
    var malt: List<Malt>,
    var hops: List<Hops>,
    var yeast: String?,
) : Parcelable {
    @Parcelize
    data class Amount(
        var value: Double,
        var unit: MeasurementUnit,
    ) : Parcelable

    @Parcelize
    data class Malt(
        var name: String,
        var amount: Amount,
    ) : Parcelable

    @Parcelize
    data class Hops(
        var name: String,
        var amount: Amount,
        var add: String,
        var attribute: String,
    ) : Parcelable
}