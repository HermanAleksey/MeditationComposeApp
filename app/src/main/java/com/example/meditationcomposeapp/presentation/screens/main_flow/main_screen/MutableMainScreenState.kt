package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel

@Stable
interface MainScreenState {
    val updateNotesDialogVisible: Boolean
    val updateNotesList: List<UpdateDescriptionModel>
}

class MutableMainScreenState : MainScreenState {
    override var updateNotesDialogVisible: Boolean by mutableStateOf(false)
    override var updateNotesList: List<UpdateDescriptionModel> by mutableStateOf(listOf())
}