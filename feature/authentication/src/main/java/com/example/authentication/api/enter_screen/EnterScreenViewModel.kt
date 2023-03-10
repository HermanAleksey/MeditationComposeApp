package com.example.authentication.api.enter_screen

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.NavigationBaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EnterScreenViewModel @Inject constructor() : NavigationBaseViewModel<EnterScreenNavRoute>() {
    fun onEnterClick() = viewModelScope.launch {
        navigationEventTransaction {
            _navigationEvent.emit(
                EnterScreenNavRoute.LoginScreen
            )
        }
    }

    fun onDontHaveAccountClick() = viewModelScope.launch {
        navigationEventTransaction {
            _navigationEvent.emit(
                EnterScreenNavRoute.RegistrationScreen
            )
        }
    }
}