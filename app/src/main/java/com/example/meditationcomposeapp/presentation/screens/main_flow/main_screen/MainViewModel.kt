package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val updateDescriptionRepository: UpdateDescriptionRepository,
) : ViewModel() {

    private var _uiState by mutableStateOf(MainScreenState())
    val uiState = _uiState

    init {
        viewModelScope.launch {
            updateDescriptionRepository.getLastUpdate()?.let { lastUpdateDesc ->
//                if (!lastUpdateDesc.wasShown) {
                    _uiState = _uiState.copy(
                        updateNotesDialogVisible = true,
                        updateNotesList = listOf(lastUpdateDesc)
                    )
//                }
            }
        }
    }
}