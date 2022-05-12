package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.RequestPasswordRestorationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterLoginScreenViewModel @Inject constructor(
    private val requestPasswordRestorationUseCase: RequestPasswordRestorationUseCase
    ) : ViewModel() {

    private var state by mutableStateOf(EnterLoginScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setEmail(value: String) {
        state = state.copy(email = value)
    }

    fun getEmail() = state.email

    fun onEmailTextChanged(value: String) {
        setEmail(value)
    }

    fun onConfirmClick(navigateToEnterCodeScreen: () -> Unit) {
        viewModelScope.launch {
            requestPasswordRestorationUseCase.invoke(state.email).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        Log.e(TAG, "${javaClass.canonicalName}: Success")
                        if (it.data!!.success)
                            navigateToEnterCodeScreen()
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
}