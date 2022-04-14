package com.example.meditationcomposeapp.presentation.screens.enter

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.data_source.repository.authentication.AuthenticationRepository
import com.example.meditationcomposeapp.presentation.navigation.Screen
import javax.inject.Inject

class EnterScreenViewModel @Inject constructor() : ViewModel() {
    fun onEnterClick(navigateToLogin: () -> Unit) {
        navigateToLogin()
    }

    fun onDontHaveAccountClick(navigateToRegistration: () -> Unit) {
        navigateToRegistration()
    }
}