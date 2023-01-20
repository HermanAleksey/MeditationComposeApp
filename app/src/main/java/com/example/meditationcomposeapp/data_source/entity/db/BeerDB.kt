package com.example.meditationcomposeapp.data_source.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer")
data class BeerDB(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "tagline") var tagline: String,
    @ColumnInfo(name = "first_brewed") var firstBrewed: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "image_url") var imageUrl: String,
    @ColumnInfo(name = "abv") var abv: Double,
    @ColumnInfo(name = "ibu") var ibu: Double?,
    @ColumnInfo(name = "target_fg") var targetFg: Int?,
    @ColumnInfo(name = "target_og") var targetOg: Double?,
    @ColumnInfo(name = "ebc") var ebc: Double?,
    @ColumnInfo(name = "srm") var srm: Double?,
    @ColumnInfo(name = "ph") var ph: Double?,
    @ColumnInfo(name = "attenuation_level") var attenuationLevel: Double?,

//    //volume
//    @ColumnInfo(name = "volume_value") var volumeValue: Int,
//    @ColumnInfo(name = "volume_unit") var volumeUnit: String,
//
//    //boil volume
//    @ColumnInfo(name = "boil_volume_value") var boilVolumeValue: Int,
//    @ColumnInfo(name = "boil_volume_unit") var boilVolumeUnit: String,

    //get from other tables by beer id
//    @ColumnInfo(name = "method") var method: MethodDB?,
//    @ColumnInfo(name = "ingredients") var ingredients: IngredientsDB?,
//    @ColumnInfo(name = "food_pairing_id") var foodPairing: List<String>,

    @ColumnInfo(name = "brewers_tips") var brewersTips: String,
    @ColumnInfo(name = "contributed_by") var contributedBy: String,
)