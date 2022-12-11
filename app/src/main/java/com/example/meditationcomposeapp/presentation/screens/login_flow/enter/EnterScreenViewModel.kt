package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
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