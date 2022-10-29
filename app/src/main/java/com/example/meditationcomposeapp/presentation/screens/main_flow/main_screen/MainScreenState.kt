package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

data class MainScreenState(
    val updateNotesDialogVisible: Boolean = false,
    val updateNotesList: List<UpdateDescriptionModel> = listOf(),
)