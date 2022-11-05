package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

@Stable
data class MainScreenState(
    val updateNotesDialogVisible: Boolean = false,
    val updateNotesList: List<UpdateDescriptionModel> = listOf()
)