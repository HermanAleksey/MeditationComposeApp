package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import androidx.lifecycle.ViewModel
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.RegistrationScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import javax.inject.Inject

class EnterScreenViewModel @Inject constructor() : ViewModel() {
    fun onEnterClick(navigator: DestinationsNavigator) {
        navigator.navigate(
            LoginScreenDestination()
        )
    }

    fun onDontHaveAccountClick(navigator: DestinationsNavigator) {
        navigator.navigate(
            RegistrationScreenDestination()
        )
    }
}