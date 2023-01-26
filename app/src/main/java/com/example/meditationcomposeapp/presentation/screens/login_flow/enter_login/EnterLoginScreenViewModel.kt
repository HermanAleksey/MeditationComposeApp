package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.Event
import com.example.common.view_model.NavigationEvent
import com.example.meditationcomposeapp.model.usecase.authentication.RequestPasswordRestorationUseCase
import com.example.meditationcomposeapp.model.utils.validation.LoginField
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterCodeScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterLoginScreenViewModel @Inject constructor(
    private val requestPasswordRestorationUseCase: RequestPasswordRestorationUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(EnterLoginScreenState())
    val uiState: StateFlow<EnterLoginScreenState> = _uiState

    fun onLoginTextChanged(value: String) {
        _uiState.update {
            it.copy(login = value)
        }
    }

    //todo how to test navigation with arguments?
    fun onConfirmClick() {
        viewModelScope.launch {
            if (isEmailValid())
                requestPasswordRestorationUseCase.invoke(_uiState.value.login).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            if (it.data!!.success)
                                _navigationEvent.update {
                                    Event(
                                        NavigationEvent.Navigate(
                                            EnterCodeScreenDestination(_uiState.value.login)
                                        )
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
                                state.copy(
                                    isLoading = it.isLoading
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun isEmailValid(): Boolean {
        LoginField(_uiState.value.login).validate().let { result ->
            _uiState.update { state ->
                state.copy(
                    loginError = result.errorMessage
                )
            }
            return result.successful
        }
    }
}