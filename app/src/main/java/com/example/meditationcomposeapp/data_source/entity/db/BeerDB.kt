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

    //volume
    @ColumnInfo(name = "volume_value") var volumeValue: Int,
    @ColumnInfo(name = "volume_unit") var volumeUnit: String,

    //boil volume
    @ColumnInfo(name = "boil_volume_value") var boilVolumeValue: Int,
    @ColumnInfo(name = "boil_volume_unit") var boilVolumeUnit: String,

    //get from other tables by beer id
//    @ColumnInfo(name = "method") var method: MethodDB?,
//    @ColumnInfo(name = "ingredients") var ingredients: IngredientsDB?,
//    @ColumnInfo(name = "food_pairing_id") var foodPairing: List<String>,

    @ColumnInfo(name = "brewers_tips") var brewersTips: String,
    @ColumnInfo(name = "contributed_by") var contributedBy: String,
) {

    @Entity(tableName = "food_pairings")
    data class FoodPairings(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "beer_id") var beerId: Int,
        @ColumnInfo(name = "pair") var pair: String,
    )

    @Entity(tableName = "method")
    data class MethodDB(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "mash_temp") var mashTemp: ArrayList<MashTempDB>,
        @ColumnInfo(name = "fermentation") var fermentation: FermentationDB?,
        @ColumnInfo(name = "twist") var twist: String?,
    ) {
        @Entity(tableName = "fermentation")
        data class FermentationDB(
            @PrimaryKey var id: Int,
            @ColumnInfo(name = "value") var value: Int,
            @ColumnInfo(name = "unit") var unit: String,
        )

        @Entity(tableName = "mash_temp")
        data class MashTempDB(
            @PrimaryKey var id: Int,
            @ColumnInfo(name = "value") var value: Int,
            @ColumnInfo(name = "unit") var unit: String,
            @ColumnInfo(name = "duration") var duration: String?,
        )
    }

    @Entity(tableName = "ingredients")
    data class IngredientsDB(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "malt") var malt: ArrayList<MaltDB>,
        @ColumnInfo(name = "hops") var hops: ArrayList<HopsDB>,
        @ColumnInfo(name = "yeast") var yeast: String?,
    ) {

        @Entity(tableName = "malt")
        data class MaltDB(
            @PrimaryKey var id: Int,
            @ColumnInfo(name = "name") var name: String?,
            @ColumnInfo(name = "value") var value: Double,
            @ColumnInfo(name = "unit") var unit: String,
        )

        @Entity(tableName = "hops")
        data class HopsDB(
            @PrimaryKey var id: Int,
            @ColumnInfo(name = "name") var name: String?,
            @ColumnInfo(name = "value") var value: Double,
            @ColumnInfo(name = "unit") var unit: String,
            @ColumnInfo(name = "add") var add: String?,
            @ColumnInfo(name = "attribute") var attribute: String?,
        )
    }
}