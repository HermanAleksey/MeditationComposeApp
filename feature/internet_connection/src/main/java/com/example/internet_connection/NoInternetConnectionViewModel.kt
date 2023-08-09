package com.example.internet_connection

import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.view_model.NavigationBaseViewModel
import kotlinx.coroutines.launch

class NoInternetConnectionViewModel :
    NavigationBaseViewModel<NoInternetConnectionNavRoute>() {

    fun onBackClick() {
        viewModelScope.launch {
            navigationEventTransaction {
                _navigationEvent.emit(
                    NoInternetConnectionNavRoute.Back
                )
            }
        }
    }
}