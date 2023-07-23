package com.example.feature_toggle.api

import androidx.lifecycle.ViewModel
import com.example.common.feature_toggle.FeatureToggle
import com.example.common.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FeatureToggleViewModel @Inject constructor() : ViewModel(),
    MviViewModel<FeatureToggleScreenState, FeatureToggleAction> {

    private val _uiState = MutableStateFlow(FeatureToggleScreenState())
    override val uiState: StateFlow<FeatureToggleScreenState> = _uiState

    override fun processAction(action: FeatureToggleAction) {
        when (action) {
            is FeatureToggleAction.ToggleClicked -> {
                processToggleClicked(action.featureToggle)
            }
        }
    }

    private fun processToggleClicked(featureToggle: FeatureToggle) {

    }
}