package com.example.feature_toggle.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mvi.MviViewModel
import com.example.common.utils.emptyString
import com.example.feature_toggle.internal.entity.FeatureToggleUiItem
import com.example.feature_toggle.internal.model.use_case.GetAllFeatureTogglesUseCase
import com.example.feature_toggle.internal.model.use_case.UpdateFeatureToggleStateUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeatureToggleScreenViewModel constructor(
    private val getAllFeatureTogglesUseCase: GetAllFeatureTogglesUseCase,
    private val updateFeatureToggleStateUseCase: UpdateFeatureToggleStateUseCase,
) : ViewModel(), MviViewModel<FeatureToggleScreenState, FeatureToggleAction> {

    private val _uiState = MutableStateFlow(FeatureToggleScreenState())
    override val uiState: StateFlow<FeatureToggleScreenState> = _uiState

    init {
        updateFeatureTogglesList()
    }

    override fun processAction(action: FeatureToggleAction) {
        when (action) {
            is FeatureToggleAction.ToggleClicked -> {
                processToggleClicked(action.featureToggle, action.isSelected)
            }
            is FeatureToggleAction.ItemLongClick -> {
                processItemLongClick(action.featureToggle)
            }
        }
    }

    private fun processToggleClicked(featureToggle: FeatureToggleUiItem, isSelected: Boolean) {
        updateFeatureToggleStateUseCase(featureToggle, isSelected)
    }

    private fun processItemLongClick(featureToggle: FeatureToggleUiItem) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    snackBarMessage = featureToggle.description
                )
            }
            delay(POP_UP_DURATION)
            _uiState.update { state ->
                state.copy(snackBarMessage = emptyString())
            }
        }
    }

    private fun updateFeatureTogglesList() {
        _uiState.update {
            it.copy(
                list = getAllFeatureTogglesUseCase.invoke()
            )
        }
    }

    companion object {
        private const val POP_UP_DURATION: Long = 3_000
    }
}