package com.example.meditationcomposeapp.presentation.screens.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.Screen

class LoginScreenViewModel : ViewModel() {

    private var _email = mutableStateOf("")

    val email: State<String> = _email

    fun setEmail(value: String) {
        _email.value = value
    }

    fun onLoginTextChanged(value: String) {
        setEmail(value)
    }

    private val _password = mutableStateOf("")

    val password: State<String> = _password

    fun setPassword(value: String) {
        _password.value = value
    }

    fun onPasswordTextChanged(value: String) {
        setPassword(value)
    }


    fun onForgotPasswordClicked(navController: NavController) {
        navController.navigate(Screen.RESTORE_PASSWORD)
    }

    fun onLoginClicked(navController: NavController) {
        //process input data , send request and etc.
//        navController.navigate(Screen.LOGIN)
    }

    fun onSignUpClicked(navController: NavController) {
        navController.navigate(Screen.REGISTRATION)
    }

}