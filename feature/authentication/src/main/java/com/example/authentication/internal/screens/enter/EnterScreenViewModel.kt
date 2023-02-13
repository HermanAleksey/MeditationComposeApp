package com.example.authentication.internal.screens.enter

import com.example.authentication.api.enter_screen.EnterScreenNavRoute
import com.example.common.view_model.NavigationBaseViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class EnterScreenViewModel @Inject constructor() : NavigationBaseViewModel<EnterScreenNavRoute>() {
    fun onEnterClick() {
        _navigationEvent.update {
            EnterScreenNavRoute.LoginScreen()
        }
    }

    fun onDontHaveAccountClick() {
        _navigationEvent.update {
            EnterScreenNavRoute.RegistrationScreen()
        }
    }
}