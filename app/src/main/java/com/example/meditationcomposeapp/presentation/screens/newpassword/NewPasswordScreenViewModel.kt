package com.example.meditationcomposeapp.presentation.screens.newpassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NewPasswordScreenViewModel : ViewModel() {

    private var _newPassword = mutableStateOf("")

    val newPassword: State<String> = _newPassword

    fun setNewPassword(value: String) {
        _newPassword.value = value
    }

    fun onNewPasswordTextChanged(value: String) {
        setNewPassword(value)
    }

    private val _repeatPassword = mutableStateOf("")

    val repeatPassword: State<String> = _repeatPassword

    fun setRepeatPassword(value: String) {
        _repeatPassword.value = value
    }

    fun onRepeatPasswordTextChanged(value: String) {
        setRepeatPassword(value)
    }

    fun onConfirmClick(navigateToLoginScreen: () -> Unit) {
        //set new password request
        //move into login screen
    }
}