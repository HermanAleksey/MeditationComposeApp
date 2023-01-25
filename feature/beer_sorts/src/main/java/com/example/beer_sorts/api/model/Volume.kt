package com.example.beer_sorts.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Volume(
    var value: Int,
    var unit: MeasurementUnit,
): Parcelable

@Parcelize
data class BoilVolume(
    var value: Int,
    var unit: MeasurementUnit,
): Parcelable