package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.compose.runtime.Stable
import com.example.core.model.updates.UpdateDescriptionModel

@Stable
data class ToolbarViewState(
    val updateNotesList: List<UpdateDescriptionModel> = listOf()
)