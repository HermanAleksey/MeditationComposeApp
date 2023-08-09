package com.justparokq.core.punk_source.api.model.web

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("first_brewed") var firstBrewed: String,
    @SerializedName("description") var description: String,
    @SerializedName("image_url") var imageUrl: String?,
    @SerializedName("abv") var abv: Double,
    @SerializedName("ibu") var ibu: Double?,
    @SerializedName("target_fg") var targetFg: Int?,
    @SerializedName("target_og") var targetOg: Double?,
    @SerializedName("ebc") var ebc: Double?,
    @SerializedName("srm") var srm: Double?,
    @SerializedName("ph") var ph: Double?,
    @SerializedName("attenuation_level") var attenuationLevel: Double?,
    @SerializedName("volume") var volume: VolumeResponse,
    @SerializedName("boil_volume") var boilVolume: BoilVolumeResponse,
    @SerializedName("method") var method: MethodResponse,
    @SerializedName("ingredients") var ingredients: IngredientsResponse,
    @SerializedName("food_pairing") var foodPairing: List<String> = arrayListOf(),
    @SerializedName("brewers_tips") var brewersTips: String,
    @SerializedName("contributed_by") var contributedBy: String,
) {

    data class VolumeResponse(
        @SerializedName("value") var value: Int,
        @SerializedName("unit") var unit: String,
    )

    data class BoilVolumeResponse(
        @SerializedName("value") var value: Int,
        @SerializedName("unit") var unit: String,
    )

    data class MethodResponse(
        @SerializedName("mash_temp") var mashTemp: ArrayList<MashTempResponse>,
        @SerializedName("fermentation") var fermentation: FermentationResponse?,
        @SerializedName("twist") var twist: String?,
    ) {

        data class TempResponse(
            @SerializedName("value") var value: Int,
            @SerializedName("unit") var unit: String,
        )

        data class FermentationResponse(
            @SerializedName("temp") var temp: TempResponse?,
        )

        data class MashTempResponse(
            @SerializedName("temp") var temp: TempResponse?,
            @SerializedName("duration") var duration: String?,
        )
    }

    data class IngredientsResponse(
        @SerializedName("malt") var malt: ArrayList<MaltResponse>,
        @SerializedName("hops") var hops: ArrayList<HopsResponse>,
        @SerializedName("yeast") var yeast: String?,
    ) {
        data class AmountResponse(
            @SerializedName("value") var value: Double,
            @SerializedName("unit") var unit: String,
        )

        data class MaltResponse(
            @SerializedName("name") var name: String?,
            @SerializedName("amount") var amount: AmountResponse?,
        )

        data class HopsResponse(
            @SerializedName("name") var name: String?,
            @SerializedName("amount") var amount: AmountResponse?,
            @SerializedName("add") var add: String?,
            @SerializedName("attribute") var attribute: String?,
        )
    }
}