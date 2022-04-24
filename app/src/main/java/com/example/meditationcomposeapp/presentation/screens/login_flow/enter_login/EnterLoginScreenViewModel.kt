package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EnterLoginScreenViewModel : ViewModel() {

    private var state by mutableStateOf(EnterLoginScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setEmail(value: String) {
        state = state.copy(email = value)
    }

    fun getEmail() = state.email

    fun onEmailTextChanged(value: String) {
        setEmail(value)
    }

    fun onConfirmClick(navigateToEnterCodeScreen: () -> Unit) {
        //move into enter code screen
        navigateToEnterCodeScreen()
    }
}