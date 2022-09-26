package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.RequestPasswordRestorationUseCase
import com.example.meditationcomposeapp.model.utils.validation.LoginField
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterCodeScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterLoginScreenViewModel @Inject constructor(
    private val requestPasswordRestorationUseCase: RequestPasswordRestorationUseCase
) : ViewModel() {

    var state by mutableStateOf(EnterLoginScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    fun onLoginTextChanged(value: String) {
        state = state.copy(login = value)
    }

    fun onConfirmClick(navigator: DestinationsNavigator) {
        viewModelScope.launch {
            if (isEmailValid())
                requestPasswordRestorationUseCase.invoke(state.login).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            printEventLog("EnterLoginScreen", "Success")
                            if (it.data!!.success)
                                navigator.navigate(
                                    EnterCodeScreenDestination(state.login)
                                )
                            else {
                                //displayError()
                            }
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                            printEventLog("EnterLoginScreen", "Error")
                        }
                        is NetworkResponse.Loading<*> -> {
                            printEventLog("EnterLoginScreen", "Loading:${it.isLoading}")
                            setLoading(it.isLoading)
                        }
                    }
                }
        }
    }

    private fun isEmailValid(): Boolean {
        LoginField(state.login).validate().let {
            state = state.copy(emailError = it.errorMessage)
            return it.successful
        }
    }
}