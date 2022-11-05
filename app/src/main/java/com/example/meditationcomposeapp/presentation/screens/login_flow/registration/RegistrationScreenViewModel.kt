package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationScreenState())
    val uiState: StateFlow<RegistrationScreenState> = _uiState


    fun onNameTextChanged(value: String) {
        _uiState.update {
            it.copy(
                name = value
            )
        }
    }

    fun onLoginTextChanged(value: String) {
        _uiState.update {
            it.copy(
                login = value
            )
        }
    }

    fun onPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    fun onSignUpClicked(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            if (
                isNameFieldValid() &&
                isLoginFieldValid() &&
                isPasswordFieldValid()
            )
                registerUseCase.invoke(_uiState.value.name, _uiState.value.login, _uiState.value.password).collect {
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
                            _uiState.update { state ->
                                state.copy(isLoading = it.isLoading)
                            }
                        }
                    }
                }
        }
    }

    private fun isNameFieldValid(): Boolean {
        NameField(_uiState.value.name).validate().let {
            _uiState.update {  state ->
                state.copy(nameError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun isLoginFieldValid(): Boolean {
        //todo validate login
        LoginField(_uiState.value.name).validate().let {
            _uiState.update { state->
                state.copy(loginError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun isPasswordFieldValid(): Boolean {
        //todo validate password
        PasswordField(_uiState.value.name).validate().let {
            _uiState.update { state ->
                state.copy(passwordError = it.errorMessage)
            }
            return it.successful
        }
    }

    fun onSignInClicked(navigator: DestinationsNavigator) {
        navigator.navigate(
            LoginScreenDestination()
        )
    }
}