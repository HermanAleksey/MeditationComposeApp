package com.example.meditationcomposeapp.model.entity.updates_log

data class UpdateDescriptionModel(
    val versionName: String,
    val updateReleaseTime: Long,
    val updateTitle: String,
    val updateDescription: String,
    val wasShown: Boolean
)