package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.usecase.authentication.ClearAuthDataUseCase
import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.SplashScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val clearAuthDataUseCase: ClearAuthDataUseCase,
) : BaseViewModel() {

    fun onLogOutClicked() {
        viewModelScope.launch {
            clearAuthDataUseCase()
            _navigationEvent.update {
                Event(
                    NavigationEvent.NavigateWithPop(
                        direction = EnterScreenDestination(),
                        popUpTo = SplashScreenDestination(),
                        inclusive = false
                    )
                )
            }
        }
    }
}