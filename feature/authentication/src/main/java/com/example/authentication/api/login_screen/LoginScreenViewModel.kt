package com.example.authentication.api.login_screen

import androidx.lifecycle.viewModelScope
import com.example.authentication.internal.screens.login.LoginAction
import com.example.authentication.internal.validation.LoginField
import com.example.authentication.internal.validation.PasswordField
import com.example.common.mvi.MviAction
import com.example.common.mvi.MviViewModel
import com.example.common.view_model.NavigationBaseViewModel
import com.example.core.authentication_source.api.use_case.LoginUseCase
import com.example.core.data_store.UserDataStore
import com.example.core.model.NetworkResponse
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
) : NavigationBaseViewModel<LoginScreenNavRoute>(), MviViewModel<LoginScreenState> {

    private val _uiState = MutableStateFlow(LoginScreenState())
    override val uiState: StateFlow<LoginScreenState> = _uiState

    override fun processAction(action: MviAction) {
        when (action) {
            is LoginAction.OnLoginTextChanged -> {
                onLoginTextChanged(action.text)
            }
            is LoginAction.OnPasswordTextChanged -> {
                onPasswordTextChanged(action.text)
            }
            is LoginAction.OnLoginClicked -> {
                onLoginClicked()
            }
            is LoginAction.OnForgotPasswordClicked -> {
                onForgotPasswordClicked()
            }
            is LoginAction.OnSignUpClicked -> {
                onSignUpClicked()
            }
        }
    }

    fun onLoginTextChanged(value: String) {
        _uiState.update {
            it.copy(login = value)
        }
    }

    fun onPasswordTextChanged(value: String) {
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

    fun onLoginClicked() {
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

    fun onSignUpClicked() = viewModelScope.launch {
        navigationEventTransaction {
            _navigationEvent.emit(
                LoginScreenNavRoute.RegistrationScreen
            )
        }
    }
}