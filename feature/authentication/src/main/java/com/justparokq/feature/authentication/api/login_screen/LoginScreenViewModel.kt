package com.justparokq.feature.authentication.api.login_screen

import androidx.lifecycle.viewModelScope
import com.justparokq.feature.authentication.internal.validation.LoginField
import com.justparokq.feature.authentication.internal.validation.PasswordField
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.core.common.view_model.NavigationBaseViewModel
import com.justparokq.core.authentication_source.api.use_case.LoginUseCase
import com.justparokq.core.data_store.user.UserDataStore
import com.justparokq.core.model.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val loginUseCase: LoginUseCase,
) : NavigationBaseViewModel<LoginScreenNavRoute>(), MviViewModel<LoginScreenState, LoginAction> {

    private val _uiState = MutableStateFlow(LoginScreenState())
    override val uiState: StateFlow<LoginScreenState> = _uiState

    override fun processAction(action: LoginAction) {
        when (action) {
            is LoginAction.LoginTextChanged -> {
                onLoginTextChanged(action.text)
            }
            is LoginAction.PasswordTextChanged -> {
                onPasswordTextChanged(action.text)
            }
            is LoginAction.LoginClick -> {
                onLoginClicked()
            }
            is LoginAction.ForgotPasswordClick -> {
                onForgotPasswordClicked()
            }
            is LoginAction.SignUpClick -> {
                onSignUpClicked()
            }
        }
    }

    private fun onLoginTextChanged(value: String) {
        _uiState.update {
            it.copy(login = value)
        }
    }

    private fun onPasswordTextChanged(value: String) {
        _uiState.update { state ->
            state.copy(password = value)
        }
    }

    private fun onForgotPasswordClicked() = viewModelScope.launch {
        navigationEventTransaction {
            _navigationEvent.emit(
                LoginScreenNavRoute.EnterLoginScreen(_uiState.value.login)
            )
        }
    }

    private fun onLoginClicked() {
        val login = _uiState.value.login
        val password = _uiState.value.password

        if (validateEmailField(login) && validatePasswordField(password)) {
            viewModelScope.launch {
                loginUseCase.invoke(login, password).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            saveCreditsOnDataStore(login, password)

                            navigationEventTransaction {
                                _navigationEvent.emit(
                                    LoginScreenNavRoute.MainScreen
                                )
                            }
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                        }
                        is NetworkResponse.Loading<*> -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = it.isLoading
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun saveCreditsOnDataStore(
        login: String,
        password: String,
    ) {
        with(userDataStore) {
            writeLogin(login)
            writePassword(password)
        }
    }

    private fun validatePasswordField(password: String): Boolean {
        PasswordField(password).validate().let {
            _uiState.update { state ->
                state.copy(passwordError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun validateEmailField(email: String): Boolean {
        LoginField(email).validate().let {
            _uiState.update { state ->
                state.copy(loginError = it.errorMessage)
            }
            return it.successful
        }
    }

    private fun onSignUpClicked() = viewModelScope.launch {
        navigationEventTransaction {
            _navigationEvent.emit(
                LoginScreenNavRoute.RegistrationScreen
            )
        }
    }
}