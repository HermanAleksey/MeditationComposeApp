package com.example.meditationcomposeapp.data_source.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

@Entity(tableName = "updates_log")
data class UpdateDescriptionDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "version_name") val versionName: String,
    @ColumnInfo(name = "release_time") val updateReleaseTime: Long,
    @ColumnInfo(name = "title") val updateTitle: String,
    @ColumnInfo(name = "description") val updateDescription: String,

    @ColumnInfo(name = "shown") val isUpdateWasShown: Boolean,
)

fun UpdateDescriptionModel.toDbEntity(): UpdateDescriptionDBEntity =
    UpdateDescriptionDBEntity(
        id = 0,
        this.versionName,
        this.updateReleaseTime,
        this.updateTitle,
        this.updateDescription,
        false,
    )

fun UpdateDescriptionDBEntity.toUiModel() = UpdateDescriptionModel(
    this.versionName,
    this.updateReleaseTime,
    this.updateTitle,
    this.updateDescription,
    this.isUpdateWasShown
)