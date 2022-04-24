package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.meditationcomposeapp.model.usecase.authentication.VerifyCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnterCodeScreenViewModel @Inject constructor(
    private val verifyCodeUseCase: VerifyCodeUseCase
): ViewModel() {

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
        navigateToNewPasswordScreen()
    }


}