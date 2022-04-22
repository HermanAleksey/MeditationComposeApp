package com.example.meditationcomposeapp.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.model.utils.FieldType
import com.example.meditationcomposeapp.model.utils.FieldValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val fieldValidator: FieldValidator
) : ViewModel() {

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


    fun onForgotPasswordClicked(navigateToRestorePasswordScreen: () -> Unit) {
        navigateToRestorePasswordScreen()
    }

    fun onLoginClicked(navigateToMainScreen: () -> Unit) {
        val email = _email.value
        val password = _password.value

        //todo replace with real logic
        if (fieldValidator.validate(FieldType.Login, email)
            || fieldValidator.validate(FieldType.Password, password)
        ) {
            //        loginUseCase(email, password)
            // on success
            navigateToMainScreen()
            //on error show pop-up
        }

    }

    fun onSignUpClicked(navigateToRegistrationScreen: () -> Unit) {
        navigateToRegistrationScreen()
    }
}