package com.example.core.model.beer_sorts

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
        var add: AddOrder,
        var attribute: String,
    ) : Parcelable

    enum class AddOrder {
        START, MIDDLE, END,
        //NA might occur since api is not really stable
        NA
    }
}