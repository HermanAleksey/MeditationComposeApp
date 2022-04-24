package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.model.utils.FieldValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val fieldValidator: FieldValidator
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setEmail(value: String) {
        state = state.copy(email = value)
    }

    fun onLoginTextChanged(value: String) {
        setEmail(value)
    }

    private fun setPassword(value: String) {
        state = state.copy(password = value)
    }

    fun onPasswordTextChanged(value: String) {
        setPassword(value)
    }

    fun onForgotPasswordClicked(navigateToRestorePasswordScreen: () -> Unit) {
        navigateToRestorePasswordScreen()
    }

    fun onLoginClicked(navigateToMainScreen: () -> Unit) {
        val email = state.email
        val password = state.password

        //todo replace with real logic
        if (true
//            fieldValidator.validate(FieldType.Login, email)
//            || fieldValidator.validate(FieldType.Password, password)
        ) {
            viewModelScope.launch {
                Log.e(TAG, "onLoginClicked: login:$email, password:$password")
                loginUseCase.invoke(email, password).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            Log.e(TAG, "onLoginClicked: Success")
//                            navigateToMainScreen()
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                            Log.e(TAG, "onLoginClicked: Error")
                        }
                        is NetworkResponse.Loading<*> -> {
                            setLoading(it.isLoading)
                            Log.e(TAG, "onLoginClicked: Loading:${it.isLoading}")
                        }
                    }
                }
            }
        }
    }

    val TAG = "TAGG"

    fun onSignUpClicked(navigateToRegistrationScreen: () -> Unit) {
        navigateToRegistrationScreen()
    }
}