package com.example.meditationcomposeapp.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.model.utils.FieldType
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
                            it.isLoading
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