package com.example.meditationcomposeapp.model.entity.beer

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