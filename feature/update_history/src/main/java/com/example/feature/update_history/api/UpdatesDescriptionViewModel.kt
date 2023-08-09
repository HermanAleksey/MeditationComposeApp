package com.example.feature.update_history.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justparokq.core.updates_history.use_case.GetAllUpdatesDescriptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdatesDescriptionViewModel @Inject constructor(
    private val getAllUpdatesDescriptionsUseCase: GetAllUpdatesDescriptionsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UpdatesDescriptionViewState> =
        MutableStateFlow(UpdatesDescriptionViewState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllUpdatesDescriptionsUseCase().let { list ->
                _uiState.update {
                    it.copy(
                        updatesList = list
                    )
                }
            }
        }
    }

    fun onCancelRateUsClick() {
        _uiState.update {
            it.copy(
                isRateUsCardVisible = false
            )
        }
    }
}