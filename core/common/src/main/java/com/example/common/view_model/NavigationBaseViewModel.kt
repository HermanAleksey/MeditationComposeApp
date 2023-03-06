package com.example.common.view_model

import androidx.lifecycle.ViewModel
import com.example.common.navigation.NavRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class NavigationBaseViewModel<NavRoute> : ViewModel() {

    protected val _navigationEvent: MutableStateFlow<NavRoute?> =
        MutableStateFlow(
            null
        )
    val navigationEvent = _navigationEvent.asStateFlow()

    protected val _isTransactionInProgress = MutableStateFlow(false)
    val isTransactionInProgress = _isTransactionInProgress.asStateFlow()

    fun onNavigationPerformed() {
        _navigationEvent.update {
            null
        }
    }
}