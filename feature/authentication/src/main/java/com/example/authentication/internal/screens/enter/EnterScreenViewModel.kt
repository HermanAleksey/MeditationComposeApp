package com.example.authentication.internal.screens.enter

import com.example.common.view_model.BaseViewModel
import com.example.common.view_model.Event
import com.example.common.view_model.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.RegistrationScreenDestination
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class EnterScreenViewModel @Inject constructor() : BaseViewModel() {
    fun onEnterClick() {
        _navigationEvent.update {
            Event(
                NavigationEvent.Navigate(
                    LoginScreenDestination()
                )
            )
        }
    }

    fun onDontHaveAccountClick() {
        _navigationEvent.update {
            Event(
                NavigationEvent.Navigate(
                    RegistrationScreenDestination()
                )
            )
        }
    }
}