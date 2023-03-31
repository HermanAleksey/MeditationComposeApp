package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.NavigationBaseViewModel
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
) : NavigationBaseViewModel<ToolbarNavRoute>() {

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
        navigationEventTransaction {
            _navigationEvent.emit(
                ToolbarNavRoute.UpdatesHistory
            )
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
