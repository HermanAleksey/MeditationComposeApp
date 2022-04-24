package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegistrationScreenViewModel : ViewModel() {

    private var state by mutableStateOf(RegistrationScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setName(value: String) {
        state = state.copy(
            name = value
        )
    }

    fun getName() = state.name

    fun onNameTextChanged(value: String) {
        setName(value)
    }


    private fun setEmail(value: String) {
        state = state.copy(
            email = value
        )
    }

    fun getEmail() = state.email

    fun onLoginTextChanged(value: String) {
        setEmail(value)
    }


    private fun setPassword(value: String) {
        state = state.copy(
            password = value
        )
    }

    fun getPassword() = state.password

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