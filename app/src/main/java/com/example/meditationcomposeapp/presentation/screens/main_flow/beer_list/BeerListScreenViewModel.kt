package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.random_data.GetBeerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListScreenViewModel @Inject constructor(
    val getBeerListUseCase: GetBeerListUseCase
) : ViewModel() {

    private var state by mutableStateOf(BeerListScreenState())

    fun isLoading() = state.isLoading

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(
            isLoading = isLoading
        )
    }

    fun getBeerList() = state.beerList

    private fun setBeerList(list: List<Beer>) {
        state = state.copy(
            beerList = list
        )
    }

    fun onFirstLaunch(){
        loadBeerList()
    }

   private fun loadBeerList() {
        viewModelScope.launch {
            getBeerListUseCase(10).collect { networkResponse ->
                when (networkResponse) {
                    is NetworkResponse.Success<*> -> {
                        printEventLog("BeerListScreen", "Success:${networkResponse.data}")
                        if (networkResponse.data != null)
                            setBeerList(networkResponse.data)
                    }
                    is NetworkResponse.Failure<*> -> {
                        //on error show pop-up
                        printEventLog("BeerListScreen", "Error")
                    }
                    is NetworkResponse.Loading<*> -> {
                        printEventLog("BeerListScreen", "Loading:${networkResponse.isLoading}")
                        setLoading(networkResponse.isLoading)
                    }
                }

            }
        }
    }
}