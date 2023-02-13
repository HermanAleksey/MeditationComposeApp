package com.example.common.view_model

import androidx.lifecycle.ViewModel
import com.example.common.navigation.NavDependencies
import com.example.common.navigation.NavRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class NavigationBaseViewModel<NavRoute> : ViewModel() {

    protected val _navigationEvent: MutableStateFlow<NavRoute?> =
        MutableStateFlow(
            null
        )
    val navigationEvent = _navigationEvent.asStateFlow()
}