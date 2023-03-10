package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "updates_log")
data class UpdateDescriptionDBEntity(
    @PrimaryKey @ColumnInfo(name = "version_name") val versionName: String,
    @ColumnInfo(name = "release_time") val updateReleaseTime: Long,
    @ColumnInfo(name = "title") val updateTitle: String,
    @ColumnInfo(name = "description") val updateDescription: String,

    @ColumnInfo(name = "shown") val isUpdateWasShown: Boolean,
)