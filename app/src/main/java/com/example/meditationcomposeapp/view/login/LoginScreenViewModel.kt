package com.example.meditationcomposeapp.view.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

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


    fun onForgotPasswordClicked() {

    }

    fun onLoginClicked() {

    }

    fun onSignUpClicked() {

    }

}