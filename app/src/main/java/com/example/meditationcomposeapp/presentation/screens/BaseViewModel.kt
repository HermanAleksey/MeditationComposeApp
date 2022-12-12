package com.example.meditationcomposeapp.presentation.screens

import androidx.lifecycle.ViewModel
import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected val _navigationEvent: MutableStateFlow<Event<NavigationEvent>> = MutableStateFlow(
        Event(NavigationEvent.Empty)
    )
    val navigationEvent = _navigationEvent.asStateFlow()
}