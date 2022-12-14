package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.compose.runtime.Stable
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel

@Stable
data class ToolbarViewState(
    val updateNotesList: List<UpdateDescriptionModel> = listOf()
)