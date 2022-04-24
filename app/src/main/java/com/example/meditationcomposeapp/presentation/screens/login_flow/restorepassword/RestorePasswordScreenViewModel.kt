package com.example.meditationcomposeapp.presentation.screens.login_flow.restorepassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RestorePasswordScreenViewModel : ViewModel() {

    private var state by mutableStateOf(RestorePasswordScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    fun getCode() = state.code

    fun setDigit(index: Int, value: Int) {
        val newCodeState = state.code.copyOf()
        newCodeState[index] = value

        state = state.copy(
            code = newCodeState
        )
    }

    fun onLastDigitFilled(navigateToNewPasswordScreen: () -> Unit) {
        navigateToNewPasswordScreen()
    }


}