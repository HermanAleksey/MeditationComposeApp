package com.justparokq.core.model.beer_sorts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Method(
    var mashTemp: List<MashTemp>,
    var fermentation: Fermentation,
    var twist: String?,
) : Parcelable {

    @Parcelize
    data class Temp(
        var value: Int?,
        var unit: MeasurementUnit,
    ) : Parcelable

    @Parcelize
    data class Fermentation(
        var temp: Temp?,
    ) : Parcelable

    @Parcelize
    data class MashTemp(
        var temp: Temp?,
        var duration: String?,
    ) : Parcelable
}