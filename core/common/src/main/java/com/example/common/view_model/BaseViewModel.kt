package com.example.common.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected val _navigationEvent: MutableStateFlow<Event<NavigationEvent>> = MutableStateFlow(
        Event(NavigationEvent.Empty)
    )
    val navigationEvent = _navigationEvent.asStateFlow()
}