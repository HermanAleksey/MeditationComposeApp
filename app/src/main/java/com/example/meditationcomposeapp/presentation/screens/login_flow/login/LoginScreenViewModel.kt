package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.model.utils.validation.LoginField
import com.example.meditationcomposeapp.model.utils.validation.PasswordField
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterLoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private var state by mutableStateOf(LoginScreenState())

    fun isLoading() = state.isLoading
    fun getLogin() = state.login
    fun getLoginError() = state.loginError
    fun getPassword() = state.password
    fun getPasswordError() = state.passwordError


    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun onLoginTextChanged(value: String) {
        state = state.copy(login = value)
    }

    fun onPasswordTextChanged(value: String) {
        state = state.copy(password = value)
    }

    fun onForgotPasswordClicked(navigator: DestinationsNavigator) {
        navigator.navigate(
            EnterLoginScreenDestination(state.login)
        )
    }

    fun onLoginClicked(navigator: DestinationsNavigator) {
        val login = state.login
        val password = state.password

        if (validateEmailField(login) && validatePasswordField(password)) {
            viewModelScope.launch {
                loginUseCase.invoke(login, password).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            printEventLog("LoginScreen", "Success")
                            saveCreditsOnDataStore(login, password)
                            navigator.navigate(
                                MainScreenDestination()
                            )
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

    private suspend fun saveCreditsOnDataStore(login: String, password: String) {
        with(userDataStore) {
            writeLogin(login)
            writePassword(password)
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
            state = state.copy(loginError = it.errorMessage)
            return it.successful
        }
    }

    fun onSignUpClicked(navigator: DestinationsNavigator) {
        navigator.navigate(
            EnterScreenDestination()
        )
    }
}