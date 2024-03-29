package com.justparokq.feature.authentication.api.enter_screen

import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.view_model.NavigationBaseViewModel
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