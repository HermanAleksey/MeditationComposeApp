package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.VerifyCodeUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.NewPasswordScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeScreenViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase,
) : ViewModel() {

    private var state by mutableStateOf(EnterCodeScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    fun getCode() = state.code

    private fun isCodeFullyInputted() = state.code.all { it != EnterCodeScreenState.EMPTY_NUMBER }

    fun onCodeDigitChanged(index: Int, value: Int): Boolean {
        val newCodeState = state.code.copyOf()
        newCodeState[index] = value

        state = state.copy(
            code = newCodeState
        )
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
                        setLoading(it.isLoading)
                    }
                }
            }
        }
    }

    private fun clearCodeInput() {
        state = state.copy(
            code = EnterCodeScreenState.EMPTY_CODE_VALUE
        )
    }

    private fun getCodeAsString(): String {
        val code = StringBuilder("")
        state.code.forEach {
            code.append(it.toString())
        }
        return code.toString()
    }
}