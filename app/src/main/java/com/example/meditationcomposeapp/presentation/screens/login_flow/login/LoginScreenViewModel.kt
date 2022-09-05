package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.model.utils.validation.LoginField
import com.example.meditationcomposeapp.model.utils.validation.PasswordField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun onLoginTextChanged(value: String) {
        state = state.copy(email = value)
    }

    fun onPasswordTextChanged(value: String) {
        state = state.copy(password = value)
    }

    fun onForgotPasswordClicked(navigateToRestorePasswordScreen: (String) -> Unit) {
        navigateToRestorePasswordScreen(state.email)
    }

    fun onLoginClicked(navigateToMainScreen: () -> Unit) {
        val email = state.email
        val password = state.password

        if (validateEmailField(email) && validatePasswordField(password)) {
            viewModelScope.launch {
                loginUseCase.invoke(email, password).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            printEventLog("LoginScreen", "Success")
                            navigateToMainScreen()
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                            printEventLog("LoginScreen", "Error")
                        }
                        is NetworkResponse.Loading<*> -> {
                            printEventLog("LoginScreen", "Loading:${it.isLoading}")
                            setLoading(it.isLoading)
                        }
                    }
                }
            }
        }
    }

    private fun validatePasswordField(password: String): Boolean {
        PasswordField(password).validate().let {
            state = state.copy(passwordError = it.errorMessage)
            return it.successful
        }
    }

    private fun validateEmailField(email: String): Boolean {
        LoginField(email).validate().let {
            state = state.copy(emailError = it.errorMessage)
            return it.successful
        }
    }

    fun onSignUpClicked(navigateToEnterLoginScreen: () -> Unit) {
        navigateToEnterLoginScreen()
    }
}