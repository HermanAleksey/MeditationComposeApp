package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val updateDescriptionRepository: UpdateDescriptionRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState

    init {
        viewModelScope.launch {
            updateDescriptionRepository.getLastUpdate()?.let { lastUpdateDesc ->
                if (!lastUpdateDesc.wasShown) {
                    _uiState.update { state ->
                        state.copy(
                            updateNotesList = listOf(lastUpdateDesc),
                            updateNotesDialogVisible = true
                        )
                    }
                }
            }
        }
    }

    fun onDialogBackgroundClick() {
        _uiState.update {
            it.copy(
                updateNotesDialogVisible = false
            )
        }
    }

    fun onUpdateHistoryClick(): () -> Unit = {
        viewModelScope.launch {
            with(_uiState) {
                update {
                    it.copy(
                        updateNotesList = updateDescriptionRepository.getAll().reversed()
                    )
                }

                if (value.updateNotesList.isNotEmpty())
                    update {
                        it.copy(
                            updateNotesDialogVisible = true
                        )
                    }
            }
        }
    }
}