package com.example.meditationcomposeapp.presentation.screens.enter

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.navigation.Screen

class EnterScreenViewModel : ViewModel() {
    fun onEnterClick(navigateToLogin: () -> Unit) {
        navigateToLogin()
    }

    fun onDontHaveAccountClick(navigateToRegistration: () -> Unit) {
        navigateToRegistration()
    }
}