package com.example.meditationcomposeapp.model.entity.beer

enum class MeasurementUnit(val namings: Array<String>) {
    UNDEFINED(emptyArray()),

    LITRES(arrayOf("LITRES")),

    KILOGRAMS(arrayOf("KILOGRAM", "KILOGRAMS")),
    GRAMS(arrayOf("GRAMS")),

    CELSIUS(arrayOf("CELSIUS")),

    TOTAL(arrayOf("TOTAL"));
}

fun String.convertToMeasurementUnit(): MeasurementUnit {
    MeasurementUnit.values().forEach {
        if (it.namings.contains(this.uppercase()))
            return it
    }
    return MeasurementUnit.UNDEFINED
}
