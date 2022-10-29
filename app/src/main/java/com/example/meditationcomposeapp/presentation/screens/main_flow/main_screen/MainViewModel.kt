package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val updateDescriptionRepository: UpdateDescriptionRepository,
) : ViewModel() {
    fun onDialogBackgroundClick() {
        _uiState.updateNotesDialogVisible = false
    }

    private var _uiState = MutableMainScreenState()
    val iuState: MainScreenState = _uiState


    init {
        viewModelScope.launch {
            updateDescriptionRepository.getLastUpdate()?.let { lastUpdateDesc ->
//                if (!lastUpdateDesc.wasShown) {
                    _uiState.updateNotesDialogVisible = true
                    _uiState.updateNotesList = listOf(lastUpdateDesc)
//                }
            }
        }
    }
}