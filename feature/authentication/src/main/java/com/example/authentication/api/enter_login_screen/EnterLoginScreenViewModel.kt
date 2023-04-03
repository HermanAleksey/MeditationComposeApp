package com.example.authentication.api.enter_login_screen

import androidx.lifecycle.viewModelScope
import com.example.authentication.internal.screens.enter_login.EnterLoginAction
import com.example.authentication.internal.validation.LoginField
import com.example.common.mvi.MviAction
import com.example.common.mvi.MviViewModel
import com.example.common.view_model.NavigationBaseViewModel
import com.example.core.authentication_source.api.use_case.RequestPasswordRestorationUseCase
import com.example.core.model.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterLoginScreenViewModel @Inject constructor(
    private val requestPasswordRestorationUseCase: RequestPasswordRestorationUseCase,
) : NavigationBaseViewModel<EnterLoginScreenNavRoute>(), MviViewModel<EnterLoginScreenState> {

    private val _uiState = MutableStateFlow(EnterLoginScreenState())
    override val uiState: StateFlow<EnterLoginScreenState> = _uiState

    override fun processAction(action: MviAction) {
        when (action) {
            is EnterLoginAction.OnScreenEntered -> {
                onScreenOpened(action.initialLogin)
            }
            is EnterLoginAction.OnLoginTextChanged -> {
                onLoginTextChanged(action.text)
            }
            is EnterLoginAction.OnConfirmClick -> {
                onConfirmClick()
            }
        }
    }

    private fun onScreenOpened(initialLogin: String) {
        _uiState.update {
            it.copy(login = initialLogin)
        }
    }

    private fun onLoginTextChanged(value: String) {
        _uiState.update {
            it.copy(login = value)
        }
    }

    private fun onConfirmClick() {
        viewModelScope.launch {
            if (isEmailValid())
                requestPasswordRestorationUseCase.invoke(_uiState.value.login).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            if (it.data!!.success) {
                                navigationEventTransaction {
                                    _navigationEvent.emit(
                                        EnterLoginScreenNavRoute.EnterCodeScreen(login = _uiState.value.login)
                                    )
                                }
                            } else {
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