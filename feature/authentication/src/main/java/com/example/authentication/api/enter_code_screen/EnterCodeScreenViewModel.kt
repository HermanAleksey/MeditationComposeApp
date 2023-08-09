package com.example.authentication.api.enter_code_screen

import androidx.lifecycle.viewModelScope
import com.justparokq.core.common.mvi.MviViewModel
import com.justparokq.core.common.utils.emptyString
import com.justparokq.core.common.view_model.NavigationBaseViewModel
import com.justparokq.core.authentication_source.api.use_case.VerifyCodeUseCase
import com.example.core.model.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeScreenViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase,
) : NavigationBaseViewModel<EnterCodeScreenNavRoute>(),
    MviViewModel<EnterCodeScreenState, EnterCodeAction> {

    private val _uiState = MutableStateFlow(EnterCodeScreenState())
    override val uiState: StateFlow<EnterCodeScreenState> = _uiState

    override fun processAction(action: EnterCodeAction) {
        when (action) {
            is EnterCodeAction.FirstLaunch -> {
                _uiState.update {
                    it.copy(
                        login = action.login
                    )
                }
            }
            is EnterCodeAction.CodeDigitChanged -> {
                onCodeDigitChanged(action.position, action.number)
            }
            is EnterCodeAction.LastDigitFilled -> {
                onLastDigitFilled()
            }
        }
    }

    private fun isCodeFullyInputted(code: Array<Int>) =
        code.all { it != EnterCodeScreenState.EMPTY_NUMBER }

    private fun onCodeDigitChanged(index: Int, value: Int) {
        if (index < 0 || index >= _uiState.value.code.size) {
            _uiState.update {
                it.copy(
                    isCodeFullyInputted = isCodeFullyInputted(_uiState.value.code)
                )
            }
            return
        }
        val newCodeState = _uiState.value.code.copyOf()
        newCodeState[index] = value

        _uiState.update {
            it.copy(
                code = newCodeState,
                isCodeFullyInputted = isCodeFullyInputted(newCodeState)
            )
        }
    }

    private fun onLastDigitFilled() {
        viewModelScope.launch {
            verifyCodeUseCase.invoke(uiState.value.login, getCodeAsString()).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        if (it.data!!.success) {
                            navigationEventTransaction {
                                _navigationEvent.emit(
                                    EnterCodeScreenNavRoute.NewPasswordScreen(uiState.value.login)
                                )
                            }
                        } else {
                            //displayError()
                            clearCodeInput()
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

    private fun clearCodeInput() {
        _uiState.update {
            it.copy(
                code = EnterCodeScreenState.EMPTY_CODE_VALUE
            )
        }
    }

    private fun getCodeAsString(): String {
        val code = StringBuilder(emptyString())
        _uiState.value.code.forEach {
            code.append(it.toString())
        }
        return code.toString()
    }
}