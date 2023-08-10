package com.justparokq.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer")
data class BeerListItem(
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
    @ColumnInfo(name = "brewers_tips") var brewersTips: String,
    @ColumnInfo(name = "contributed_by") var contributedBy: String,
)