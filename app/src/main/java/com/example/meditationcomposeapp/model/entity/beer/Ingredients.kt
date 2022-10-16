package com.example.meditationcomposeapp.model.entity.beer

data class Ingredients(
    var malt: List<Malt>,
    var hops: List<Hops>,
    var yeast: String,
) {
    data class Amount(
        var value: Double,
        var unit: String,
    )

    data class Malt(
        var name: String,
        var amount: Amount,
    )

    data class Hops(
        var name: String,
        var amount: Amount,
        var add: String,
        var attribute: String,
    )
}