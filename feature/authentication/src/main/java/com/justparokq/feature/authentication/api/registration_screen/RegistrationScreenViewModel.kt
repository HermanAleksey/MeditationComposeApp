package com.justparokq.feature.authentication.api.registration_screen

import androidx.lifecycle.viewModelScope
import com.justparokq.feature.authentication.internal.validation.LoginField
import com.justparokq.feature.authentication.internal.validation.NameField
import com.justparokq.feature.authentication.internal.validation.PasswordField
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.core.common.view_model.NavigationBaseViewModel
import com.justparokq.core.authentication_source.api.use_case.RegisterUseCase
import com.justparokq.core.model.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : NavigationBaseViewModel<RegistrationScreenNavRoute>(),
    MviViewModel<RegistrationScreenState, RegistrationAction> {

    private val _uiState = MutableStateFlow(RegistrationScreenState())
    override val uiState: StateFlow<RegistrationScreenState> = _uiState

    override fun processAction(action: RegistrationAction) {
        when (action) {
            is RegistrationAction.LoginTextChanged -> {
                onLoginTextChanged(action.text)
            }
            is RegistrationAction.PasswordTextChanged -> {
                onPasswordTextChanged(action.text)
            }
            is RegistrationAction.NameTextChanged -> {
                onNameTextChanged(action.text)
            }
            is RegistrationAction.SignInClick -> {
                onSignInClicked()
            }
            is RegistrationAction.SignUpClick -> {
                onSignUpClicked()
            }
        }
    }

    private fun onNameTextChanged(value: String) {
        _uiState.update {
            it.copy(
                name = value
            )
        }
    }

    private fun onLoginTextChanged(value: String) {
        _uiState.update {
            it.copy(
                login = value
            )
        }
    }

    private fun onPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    private fun onSignUpClicked() {
        viewModelScope.launch {
            if (
                isNameFieldValid() &&
                isLoginFieldValid() &&
                isPasswordFieldValid()
            )
                registerUseCase.invoke(
                    _uiState.value.name,
                    _uiState.value.login,
                    _uiState.value.password
                ).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            if (it.data!!.success)
                                navigationEventTransaction {
                                    _navigationEvent.emit(
                                        RegistrationScreenNavRoute.LoginScreen
                                    )
                                }
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
            _uiState.update { state ->
                state.copy(nameError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun isLoginFieldValid(): Boolean {
        LoginField(_uiState.value.login).validate().let {
            _uiState.update { state ->
                state.copy(loginError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun isPasswordFieldValid(): Boolean {
        PasswordField(_uiState.value.password).validate().let {
            _uiState.update { state ->
                state.copy(passwordError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun onSignInClicked() = viewModelScope.launch {
        navigationEventTransaction {
            _navigationEvent.emit(
                RegistrationScreenNavRoute.LoginScreen
            )
        }
    }
}