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

    fun onEmailTextChanged(value: String) {
        state = state.copy(email = value)
    }

    fun onConfirmClick(navigateToEnterCodeScreen: () -> Unit) {
        viewModelScope.launch {
            if (isEmailValid())
                requestPasswordRestorationUseCase.invoke(state.email).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            this.javaClass.printEventLog("Success")
                            if (it.data!!.success)
                                navigateToEnterCodeScreen()
                            else {
                                //displayError()
                            }
                        }
                        is NetworkResponse.Failure<*> -> {
                            //on error show pop-up
                            this.javaClass.printEventLog("Error")
                        }
                        is NetworkResponse.Loading<*> -> {
                            this.javaClass.printEventLog("Loading:${it.isLoading}")
                            setLoading(it.isLoading)
                        }
                    }
                }
        }
    }

    private fun isEmailValid(): Boolean {
        LoginField(state.email).validate().let {
            state = state.copy(emailError = it.errorMessage)
            return it.successful
        }
    }
}