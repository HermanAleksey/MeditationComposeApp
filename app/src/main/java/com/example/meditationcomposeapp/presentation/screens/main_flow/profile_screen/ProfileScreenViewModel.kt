package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.BaseViewModel
import com.example.common.view_model.Event
import com.example.common.view_model.NavigationEvent
import com.example.core.data_store.use_case.ClearAuthDataUseCase
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
                        popUpTo = SplashScreenDestination.route,
                        inclusive = false
                    )
                )
            }
        }
    }
}