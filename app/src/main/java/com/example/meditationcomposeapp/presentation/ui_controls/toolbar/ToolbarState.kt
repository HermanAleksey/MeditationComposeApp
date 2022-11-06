package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.compose.runtime.Stable
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

@Stable
data class ToolbarState(
    val updateNotesList: List<UpdateDescriptionModel> = listOf()
)