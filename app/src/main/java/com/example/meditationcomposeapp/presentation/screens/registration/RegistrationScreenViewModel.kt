package com.example.meditationcomposeapp.presentation.screens.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.navigation.Screen

class RegistrationScreenViewModel : ViewModel() {

    private var _name = mutableStateOf("")

    val name: State<String> = _name

    fun setName(value: String) {
        _name.value = value
    }

    fun onNameTextChanged(value: String) {
        setName(value)
    }

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

    fun onSignUpClicked(navigateToLoginScreen: () -> Unit) {
        //registration logic
        // show screen with registration result?
    }

    fun onSignInClicked(navigateToLoginScreen: () -> Unit) {
        navigateToLoginScreen()
    }
}