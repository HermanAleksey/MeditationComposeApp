package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.VerifyCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

    fun setDigit(index: Int, value: Int) {
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
                        Log.e(TAG, "${javaClass.canonicalName}: Success")
                        if (it.data!!.success)
                            navigateToNewPasswordScreen()
                        else {
                            //displayError()
                        }
                    }
                    is NetworkResponse.Failure<*> -> {
                        //on error show pop-up
                        Log.e(TAG, "${javaClass.canonicalName}: Error")
                    }
                    is NetworkResponse.Loading<*> -> {
                        setLoading(it.isLoading)
                        Log.e(TAG, "${javaClass.canonicalName}: Loading:${it.isLoading}")
                    }
                }
            }
        }
    }

    private val TAG = "TAGG"

    private fun getCodeAsString(): String {
        val code = StringBuilder("")
        state.code.forEach {
            code.append(it.toString())
        }
        return code.toString()
    }
}