package com.example.meditationcomposeapp.model.entity.beer

data class Method(
    var mashTemp: List<MashTemp>,
    var fermentation: Fermentation,
    var twist: String?,
) {

    data class Temp(
        var value: Int?,
        var unit: Unit,
    )

    data class Fermentation(
        var temp: Temp?,
    )

    data class MashTemp(
        var temp: Temp?,
        var duration: String?,
    )
}