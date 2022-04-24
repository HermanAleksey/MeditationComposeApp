package com.example.meditationcomposeapp.presentation.screens.login_flow.new_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewPasswordScreenViewModel : ViewModel() {

    private var state by mutableStateOf(NewPasswordScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setNewPassword(value: String) {
        state = state.copy(newPassword = value)
    }

    fun getNewPassword() = state.newPassword

    fun onNewPasswordTextChanged(value: String) {
        setNewPassword(value)
    }

    private fun setRepeatPassword(value: String) {
        state = state.copy(repeatPassword = value)
    }

    fun getRepeatPassword() = state.repeatPassword

    fun onRepeatPasswordTextChanged(value: String) {
        setRepeatPassword(value)
    }

    fun onConfirmClick(navigateToLoginScreen: () -> Unit) {
        //set new password request
        //move into login screen
    }
}