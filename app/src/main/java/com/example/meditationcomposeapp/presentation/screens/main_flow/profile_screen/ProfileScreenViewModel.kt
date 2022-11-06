package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.usecase.authentication.ClearAuthDataUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val clearAuthDataUseCase: ClearAuthDataUseCase,
) : ViewModel() {

    fun onLogOutClicked(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            clearAuthDataUseCase()
            navigator.navigate(
                EnterScreenDestination()
            ) {
                popUpTo(SplashScreenDestination().route)
            }
        }
    }
}