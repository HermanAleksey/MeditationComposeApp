package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.RegisterUseCase
import com.example.meditationcomposeapp.model.utils.FieldValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val fieldValidator: FieldValidator
) : ViewModel() {

    private var state by mutableStateOf(RegistrationScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setName(value: String) {
        state = state.copy(
            name = value
        )
    }

    fun getName() = state.name

    fun onNameTextChanged(value: String) {
        setName(value)
    }


    private fun setEmail(value: String) {
        state = state.copy(
            email = value
        )
    }

    fun getEmail() = state.email

    fun onLoginTextChanged(value: String) {
        setEmail(value)
    }


    private fun setPassword(value: String) {
        state = state.copy(
            password = value
        )
    }

    fun getPassword() = state.password

    fun onPasswordTextChanged(value: String) {
        setPassword(value)
    }

    fun onSignUpClicked(navigateToLoginScreen: () -> Unit) {
        viewModelScope.launch {
            registerUseCase.invoke(state.name, state.email, state.password).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        Log.e(TAG, "${javaClass.canonicalName}: Success")
                        if (it.data!!.success)
                            navigateToLoginScreen()
                        else {
                            //displayError()
                        }
                    }
                    is NetworkResponse.Failure<*> -> {
                        //on error show pop-up
                        Log.e(TAG, "${javaClass.canonicalName}: Error")
                    }
                    is NetworkResponse.Loading<*> -> {
                        setLoading(it.isLoading)
                        Log.e(TAG, "${javaClass.canonicalName}: Loading:${it.isLoading}")
                    }
                }
            }
        }
    }

    private val TAG = "TAGG"

    fun onSignInClicked(navigateToLoginScreen: () -> Unit) {
        navigateToLoginScreen()
    }
}