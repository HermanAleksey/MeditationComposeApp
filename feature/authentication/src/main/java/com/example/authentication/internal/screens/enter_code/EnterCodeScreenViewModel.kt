package com.example.authentication.internal.screens.enter_code

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.BaseViewModel
import com.example.core.authentication_source.api.use_case.VerifyCodeUseCase
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
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(EnterCodeScreenState())
    val uiState: StateFlow<EnterCodeScreenState> = _uiState

    private fun isCodeFullyInputted() =
        _uiState.value.code.all { it != EnterCodeScreenState.EMPTY_NUMBER }

    fun onCodeDigitChanged(index: Int, value: Int): Boolean {
        if (index < 0 || index >= _uiState.value.code.size) {
            return isCodeFullyInputted()
        }
        val newCodeState = _uiState.value.code.copyOf()
        newCodeState[index] = value

        _uiState.update {
            it.copy(
                code = newCodeState
            )
        }
        return isCodeFullyInputted()
    }

    fun onLastDigitFilled(
        login: String
    ) {
        viewModelScope.launch {
            verifyCodeUseCase.invoke(login, getCodeAsString()).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        if (it.data!!.success)
//                            _navigationEvent.update {
//                                Event(
//                                    NavigationEvent.NavigateWithPop(
//                                        direction = NewPasswordScreenDestination(login),
//                                        popUpTo = LoginScreenDestination,
//                                        inclusive = false
//                                    )
//                                )
//                            }
                        else {
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
        val code = StringBuilder("")
        _uiState.value.code.forEach {
            code.append(it.toString())
        }
        return code.toString()
    }
}