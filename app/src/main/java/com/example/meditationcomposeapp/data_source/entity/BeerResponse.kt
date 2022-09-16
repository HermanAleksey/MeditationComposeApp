package com.example.meditationcomposeapp.data_source.entity

import com.google.gson.annotations.SerializedName


data class BeerResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("style") var style: String? = null,
    @SerializedName("hop") var hop: String? = null,
    @SerializedName("yeast") var yeast: String? = null,
    @SerializedName("malts") var malts: String? = null,
    @SerializedName("ibu") var ibu: String? = null,
    @SerializedName("alcohol") var alcohol: String? = null,
    @SerializedName("blg") var blg: String? = null
)