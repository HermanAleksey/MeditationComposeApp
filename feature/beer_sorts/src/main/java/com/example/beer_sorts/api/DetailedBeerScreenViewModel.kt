package com.example.beer_sorts.api

import androidx.lifecycle.viewModelScope
import com.example.beer_sorts.internal.presentation.beer_details.DetailedBeerScreenState
import com.example.common.view_model.BaseViewModel
import com.example.core.model.NetworkResponse
import com.example.punk_source.api.use_case.punk.network.GetBeerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailedBeerScreenViewModel @Inject constructor(
    private val getBeerByIdUseCase: GetBeerByIdUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(DetailedBeerScreenState())
    val uiState = _uiState.asStateFlow()

    fun onScreenOpened(beerId: Int) = viewModelScope.launch {
        getBeerByIdUseCase(beerId).collect { response ->
            when (response) {
                is NetworkResponse.Success<*> -> {
                    _uiState.update {
                        it.copy(
                            beer = response.data
                        )
                    }
                }
                is NetworkResponse.Failure<*> -> {
                    //on error show pop-up
                }
                is NetworkResponse.Loading<*> -> {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = response.isLoading
                        )
                    }
                }
            }
        }
    }
}