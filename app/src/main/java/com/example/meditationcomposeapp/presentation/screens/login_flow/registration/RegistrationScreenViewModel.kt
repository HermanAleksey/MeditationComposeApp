package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.RegisterUseCase
import com.example.meditationcomposeapp.model.utils.validation.LoginField
import com.example.meditationcomposeapp.model.utils.validation.NameField
import com.example.meditationcomposeapp.model.utils.validation.PasswordField
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private var state by mutableStateOf(RegistrationScreenState())

    fun getLogin() = state.login
    fun getLoginError() = state.loginError
    fun getName() = state.name
    fun getNameError() = state.nameError
    fun getPassword() = state.password
    fun getPasswordError() = state.passwordError

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
            login = value
        )
    }

    fun onPasswordTextChanged(value: String) {
        state = state.copy(
            password = value
        )
    }

    fun onSignUpClicked(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            if (
                isNameFieldValid() &&
                isLoginFieldValid() &&
                isPasswordFieldValid()
            )
                registerUseCase.invoke(state.name, state.login, state.password).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            if (it.data!!.success)
                                navigator.navigate(
                                    LoginScreenDestination()
                                )
                            else {
                                //displayError()
                            }
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                        }
                        is NetworkResponse.Loading<*> -> {
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
        //todo validate login
        LoginField(state.name).validate().let {
            state = state.copy(loginError = it.errorMessage)
            return it.successful
        }
    }

    private fun isPasswordFieldValid(): Boolean {
        //todo validate password
        PasswordField(state.name).validate().let {
            state = state.copy(passwordError = it.errorMessage)
            return it.successful
        }
    }

    fun onSignInClicked(navigator: DestinationsNavigator) {
        navigator.navigate(
            LoginScreenDestination()
        )
    }
}