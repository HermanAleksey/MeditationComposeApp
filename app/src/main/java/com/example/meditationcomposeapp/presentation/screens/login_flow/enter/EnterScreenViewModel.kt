package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class EnterScreenViewModel @Inject constructor() : ViewModel() {
    fun onEnterClick(navigateToLogin: () -> Unit) {
        navigateToLogin()
    }

    fun onDontHaveAccountClick(navigateToRegistration: () -> Unit) {
        navigateToRegistration()
    }
}