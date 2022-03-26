package com.example.meditationcomposeapp.presentation.screens.enter

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.Screen

class EnterScreenViewModel : ViewModel() {

    fun onLoginButtonClicked(navController: NavController) {
        navController.navigate(Screen.LOGIN)
    }

    fun onSignUpClicked(navController: NavController) {
        navController.navigate(Screen.REGISTRATION)
    }
}