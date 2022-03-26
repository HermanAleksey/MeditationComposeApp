package com.example.meditationcomposeapp.presentation.screens.enter

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.navigation.Screen

class EnterScreenViewModel : ViewModel() {

    fun onLoginButtonClicked(navController: NavController) {
        navController.navigate(Screen.Login.route)
    }

    fun onSignUpClicked(navController: NavController) {
        navController.navigate(Screen.Registration.route)
    }
}