package com.example.meditationcomposeapp.presentation.screens.login_flow.new_password

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.SetNewPasswordUseCase
import com.example.meditationcomposeapp.model.utils.FieldValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordScreenViewModel @Inject constructor(
    private val setNewPasswordUseCase: SetNewPasswordUseCase,
    private val fieldValidator: FieldValidator
) : ViewModel() {

    private var state by mutableStateOf(NewPasswordScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    private fun setNewPassword(value: String) {
        state = state.copy(newPassword = value)
    }

    fun getNewPassword() = state.newPassword

    fun onNewPasswordTextChanged(value: String) {
        setNewPassword(value)
    }

    private fun setRepeatPassword(value: String) {
        state = state.copy(repeatPassword = value)
    }

    fun getRepeatPassword() = state.repeatPassword

    fun onRepeatPasswordTextChanged(value: String) {
        setRepeatPassword(value)
    }

    fun onConfirmClick(navigateToLoginScreen: () -> Unit) {
        //TODO login hardcoded
        viewModelScope.launch {
            setNewPasswordUseCase.invoke("login", state.newPassword).collect {
                when (it) {
                    is NetworkResponse.Success<*> -> {
                        Log.e(TAG, "${javaClass.canonicalName}: Success")
                        if (it.data!!.success)
                            navigateToLoginScreen()
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