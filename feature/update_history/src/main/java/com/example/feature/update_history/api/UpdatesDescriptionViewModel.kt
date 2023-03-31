package com.example.feature.update_history.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.updates_history.use_case.GetAllUpdatesDescriptionsUseCase
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

    private val _updatesList: MutableStateFlow<List<UpdateDescriptionModel>> =
        MutableStateFlow(emptyList())
    val updateList = _updatesList.asStateFlow()

    init {
        viewModelScope.launch {
            getAllUpdatesDescriptionsUseCase().let { list ->
                _updatesList.update {
                    list
                }
            }
        }
    }
}