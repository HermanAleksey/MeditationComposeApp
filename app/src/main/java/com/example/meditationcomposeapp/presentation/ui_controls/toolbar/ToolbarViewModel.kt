package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.updates_history.source.db.UpdateDescriptionDBRepository
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogController
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToolbarViewModel @Inject constructor(
    //todo don't use repo
    private val updateDescriptionRepository: UpdateDescriptionDBRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ToolbarViewState())
    val uiState: StateFlow<ToolbarViewState> = _uiState

    fun onLaunch(dialogController: DialogController) {
        viewModelScope.launch {
            updateDescriptionRepository.getLastUpdate()?.let { lastUpdateDesc ->
                if (!lastUpdateDesc.wasShown) {
                    _uiState.update { state ->
                        state.copy(
                            updateNotesList = listOf(lastUpdateDesc),
                        )
                    }
                    dialogController.show(
                        DialogType.UpdateDescriptionDialog(
                            listOf(lastUpdateDesc)
                        )
                    )
                }
            }
        }
    }

    fun onUpdateHistoryClick(dialogController: DialogController) {
        viewModelScope.launch {
            with(_uiState) {
                update {
                    it.copy(
                        updateNotesList = updateDescriptionRepository.getAll().reversed()
                    )
                }

                if (value.updateNotesList.isNotEmpty())
                    dialogController.show(
                        DialogType.UpdateDescriptionDialog(
                            value.updateNotesList
                        )
                    )
            }
        }
    }
}