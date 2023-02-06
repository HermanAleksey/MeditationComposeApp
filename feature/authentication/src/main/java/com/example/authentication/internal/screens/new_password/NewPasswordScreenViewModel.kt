package com.example.authentication.internal.screens.new_password

import androidx.lifecycle.viewModelScope
import com.example.authentication.internal.validation.PasswordField
import com.example.common.utils.UiText
import com.example.common.view_model.BaseViewModel
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
    private val setNewPasswordUseCase: SetNewPasswordUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(NewPasswordScreenState())
    val uiState: StateFlow<NewPasswordScreenState> = _uiState

    private fun setLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun onNewPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(newPassword = value)
        }
    }

    fun onRepeatPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(repeatPassword = value)
        }
    }

    fun onConfirmClick(login: String) {
        viewModelScope.launch {
            if (isNewPasswordValid())
                setNewPasswordUseCase.invoke(login, _uiState.value.newPassword).collect {
                    when (it) {
                        is NetworkResponse.Success<*> -> {
                            if (it.data!!.success)
//                                _navigationEvent.update {
//                                    Event(
//                                        NavigationEvent.Navigate(
//                                            LoginScreenDestination
//                                        )
//                                    )
//                                }
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