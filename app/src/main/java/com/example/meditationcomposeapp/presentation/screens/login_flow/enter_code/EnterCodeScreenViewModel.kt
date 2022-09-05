package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.VerifyCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCodeScreenViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase
) : ViewModel() {

    private var state by mutableStateOf(EnterCodeScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    fun getCode() = state.code

    fun onCodeDigitChanged(index: Int, value: Int) {
        val newCodeState = state.code.copyOf()
        newCodeState[index] = value

        state = state.copy(
            code = newCodeState
        )
    }

    fun onLastDigitFilled(navigateToNewPasswordScreen: () -> Unit) {
        //TODO login has to be passed from previous screen
        viewModelScope.launch {
            verifyCodeUseCase.invoke("login", getCodeAsString()).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        printEventLog("EnterCodeScreen", "Success")
                        if (it.data!!.success)
                            navigateToNewPasswordScreen()
                        else {
                            //displayError()
                        }
                    }
                    is NetworkResponse.Failure<*> -> {
                        //on error show pop-up
                        printEventLog("EnterCodeScreen", "Error")
                    }
                    is NetworkResponse.Loading<*> -> {
                        printEventLog("EnterCodeScreen", "Loading:${it.isLoading}")
                        setLoading(it.isLoading)
                    }
                }
            }
        }
    }

    private fun getCodeAsString(): String {
        val code = StringBuilder("")
        state.code.forEach {
            code.append(it.toString())
        }
        return code.toString()
    }
}