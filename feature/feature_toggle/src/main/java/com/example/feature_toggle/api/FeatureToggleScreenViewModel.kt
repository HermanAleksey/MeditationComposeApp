package com.example.feature_toggle.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mvi.MviViewModel
import com.example.common.utils.emptyString
import com.example.core.data_store.feature_toggle.FeatureToggleDataStore
import com.example.feature_toggle.internal.model.FeatureToggleUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureToggleScreenViewModel @Inject constructor(
    private val featureToggleDataStore: FeatureToggleDataStore,
) : ViewModel(), MviViewModel<FeatureToggleScreenState, FeatureToggleAction> {

    private val _uiState = MutableStateFlow(FeatureToggleScreenState())
    override val uiState: StateFlow<FeatureToggleScreenState> = _uiState

    init {
        updateFeatureTogglesList()
    }

    override fun processAction(action: FeatureToggleAction) {
        when (action) {
            is FeatureToggleAction.ToggleClicked -> {
                processToggleClicked(action.featureToggle)
            }
            is FeatureToggleAction.ItemLongClick -> {
                processItemLongClick(action.featureToggle)
            }
        }
    }

    private fun processToggleClicked(featureToggle: FeatureToggleUiItem) {
        Log.e("TAG", "InternalFeatureToggleScreen: onClickItem")
//        _uiState.update { state ->
//            val a = state.list.
//            state
//        }
//        featureToggleDataStore.writeFeatureToggle()
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
        //todo remove
        _uiState.update {
            it.copy(
                list = listOf(
                    FeatureToggleUiItem(
                        isChecked = true,
                        title = "Web auth flow",
                        description = "When turned on - use web data source for auth flow"
                    ),
                    FeatureToggleUiItem(
                        isChecked = true,
                        title = "Test1",
                        description = "Desc1"
                    ),
                    FeatureToggleUiItem(
                        isChecked = false,
                        title = "test2",
                        description = "Desc2"
                    ),
                )
            )
        }
//        featureToggleDataStore.checkFeatureToggle()
    }

    companion object {
        private const val POP_UP_DURATION: Long = 3_000
    }
}