package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.VerifyCodeUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.NewPasswordScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EnterCodeScreenViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EnterCodeScreenState())
    val uiState: StateFlow<EnterCodeScreenState> = _uiState

    private fun isCodeFullyInputted() = _uiState.value.code.all { it != EnterCodeScreenState.EMPTY_NUMBER }

    fun onCodeDigitChanged(index: Int, value: Int): Boolean {
        if (index < 0 || index >= _uiState.value.code.size){
            Timber.e("Trying to insert item in index out of code bounds")
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

    fun onLastDigitFilled(login: String, navigator: DestinationsNavigator) {
        viewModelScope.launch {
            verifyCodeUseCase.invoke(login, getCodeAsString()).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        if (it.data!!.success)
                            navigator.navigate(
                                route = NewPasswordScreenDestination().route,
                                onlyIfResumed = false,
                                builder = {
                                    popUpTo(LoginScreenDestination().route)
                                }
                            )
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
        _uiState.update{
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