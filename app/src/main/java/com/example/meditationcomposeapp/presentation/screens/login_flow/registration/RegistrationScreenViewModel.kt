package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.RegisterUseCase
import com.example.meditationcomposeapp.model.utils.validation.LoginField
import com.example.meditationcomposeapp.model.utils.validation.NameField
import com.example.meditationcomposeapp.model.utils.validation.PasswordField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var state by mutableStateOf(RegistrationScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    fun onNameTextChanged(value: String) {
        state = state.copy(
            name = value
        )
    }

    fun onLoginTextChanged(value: String) {
        state = state.copy(
            email = value
        )
    }

    fun onPasswordTextChanged(value: String) {
        state = state.copy(
            password = value
        )
    }

    fun onSignUpClicked(navigateToLoginScreen: () -> Unit) {
        viewModelScope.launch {
            if (
                isNameFieldValid() &&
                isLoginFieldValid() &&
                isPasswordFieldValid()
            )
                registerUseCase.invoke(state.name, state.email, state.password).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            printEventLog("RegistrationScreen","Success")
                            if (it.data!!.success)
                                navigateToLoginScreen()
                            else {
                                //displayError()
                            }
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                            printEventLog("RegistrationScreen","Error")
                        }
                        is NetworkResponse.Loading<*> -> {
                            printEventLog("RegistrationScreen","Loading:${it.isLoading}")
                            setLoading(it.isLoading)
                        }
                    }
                }
        }
    }

    private fun isNameFieldValid(): Boolean {
        NameField(state.name).validate().let {
            state = state.copy(nameError = it.errorMessage)
            return it.successful
        }
    }

    private fun isLoginFieldValid(): Boolean {
        LoginField(state.name).validate().let {
            state = state.copy(emailError = it.errorMessage)
            return it.successful
        }
    }

    private fun isPasswordFieldValid(): Boolean {
        PasswordField(state.name).validate().let {
            state = state.copy(passwordError = it.errorMessage)
            return it.successful
        }
    }

    fun onSignInClicked(navigateToLoginScreen: () -> Unit) {
        navigateToLoginScreen()
    }
}