package com.example.feature_toggle.api

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.common.mvi.MviViewModel
import com.example.feature_toggle.internal.model.FeatureToggleUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FeatureToggleViewModel @Inject constructor() : ViewModel(),
    MviViewModel<FeatureToggleScreenState, FeatureToggleAction> {

    private val _uiState = MutableStateFlow(FeatureToggleScreenState())
    override val uiState: StateFlow<FeatureToggleScreenState> = _uiState

    init {
        //todo remove
        _uiState.update {
            it.copy(
                list = listOf(
                    FeatureToggleUiItem(
                        isChecked = true,
                        title = "Title",
                        description = "Desc"
                    )
                )
            )
        }
    }

    override fun processAction(action: FeatureToggleAction) {
        when (action) {
            is FeatureToggleAction.ToggleClicked -> {
                processToggleClicked(action.featureToggle)
            }
            is FeatureToggleAction.ItemLongHold -> {
                processItemLongHold(action.featureToggle)
            }
        }
    }

    private fun processToggleClicked(featureToggle: FeatureToggleUiItem) {
        Log.e("TAG", "InternalFeatureToggleScreen: onClickItem")
    }

    private fun processItemLongHold(featureToggle: FeatureToggleUiItem) {
        Log.e("TAG", "InternalFeatureToggleScreen: onItemPressAndHold")
    }
}