package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.updates_history.use_case.GetAllUpdatesDescriptionsUseCase
import com.example.core.updates_history.use_case.GetLastUpdateDescriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToolbarViewModel @Inject constructor(
    private val getLastUpdateDescriptionUseCase: GetLastUpdateDescriptionUseCase,
    private val getAllUpdatesDescriptionsUseCase: GetAllUpdatesDescriptionsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ToolbarViewState())
    val uiState: StateFlow<ToolbarViewState> = _uiState

    fun onLaunch() {
        viewModelScope.launch {
            getLastUpdateDescriptionUseCase()?.let { lastUpdateDesc ->
                _uiState.update {
                    it.copy(
                        lastUpdate = lastUpdateDesc
                    )
                }
            }
        }
    }

    fun onUpdateHistoryClick() = viewModelScope.launch {
        getAllUpdatesDescriptionsUseCase().let { list ->
            _uiState.update {
                it.copy(
                    isDialogShown = true,
                    updateNotesList = list
                )
            }
        }
    }

    fun onDialogClosed() {
        _uiState.update {
            it.copy(
                isDialogShown = false
            )
        }
    }
}
