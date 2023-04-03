package com.example.authentication.api.new_password_screen

import androidx.lifecycle.viewModelScope
import com.example.authentication.internal.screens.new_password.NewPasswordAction
import com.example.authentication.internal.validation.PasswordField
import com.example.common.mvi.MviAction
import com.example.common.mvi.MviViewModel
import com.example.common.utils.UiText
import com.example.common.view_model.NavigationBaseViewModel
import com.example.core.authentication_source.api.use_case.SetNewPasswordUseCase
import com.example.core.model.NetworkResponse
import com.example.feature.authentication.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordScreenViewModel @Inject constructor(
    private val setNewPasswordUseCase: SetNewPasswordUseCase,
) : NavigationBaseViewModel<NewPasswordScreenNavRoute>(), MviViewModel<NewPasswordScreenState> {

    private val _uiState = MutableStateFlow(NewPasswordScreenState())
    override val uiState: StateFlow<NewPasswordScreenState> = _uiState

    override fun processAction(action: MviAction) {
        when (action) {
            is NewPasswordAction.FirstLaunch -> {
                onFirstLaunch(action.login)
            }
            is NewPasswordAction.NewPasswordTextChanged -> {
                onNewPasswordTextChanged(action.text)
            }
            is NewPasswordAction.RepeatPasswordTextChanged -> {
                onRepeatPasswordTextChanged(action.text)
            }
            is NewPasswordAction.ConfirmClick -> {
                onConfirmClick()
            }
        }
    }

    private fun onFirstLaunch(login: String) {
        _uiState.update {
            it.copy(
                login = login
            )
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    private fun onNewPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(newPassword = value)
        }
    }

    private fun onRepeatPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(repeatPassword = value)
        }
    }

    private fun onConfirmClick() {
        viewModelScope.launch {
            if (isNewPasswordValid())
                setNewPasswordUseCase.invoke(uiState.value.login, uiState.value.newPassword)
                    .collect {
                        when (it) {
                            is NetworkResponse.Success<*> -> {
                                if (it.data!!.success)
                                    navigationEventTransaction {
                                        _navigationEvent.emit(
                                            NewPasswordScreenNavRoute.LoginScreen
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
                                setLoading(it.isLoading)
                            }
                        }
                    }
        }
    }

    private fun isNewPasswordValid(): Boolean {
        if (!isRepeatPasswordsMatch()) {
            _uiState.update {
                it.copy(
                    repeatPasswordError = UiText.StringResource(R.string.passwords_dont_match)
                )
            }
            return false
        }
        PasswordField(_uiState.value.newPassword).validate().let {
            _uiState.update { state ->
                state.copy(
                    newPasswordError = it.errorMessage
                )
            }
            return it.successful
        }
    }

    private fun isRepeatPasswordsMatch() =
        _uiState.value.newPassword == _uiState.value.repeatPassword
}