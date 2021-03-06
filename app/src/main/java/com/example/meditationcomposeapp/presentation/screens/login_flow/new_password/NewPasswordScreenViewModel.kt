package com.example.meditationcomposeapp.presentation.screens.login_flow.new_password

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.SetNewPasswordUseCase
import com.example.meditationcomposeapp.model.utils.resources.UiText
import com.example.meditationcomposeapp.model.utils.validation.PasswordField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordScreenViewModel @Inject constructor(
    private val setNewPasswordUseCase: SetNewPasswordUseCase
) : ViewModel() {

    var state by mutableStateOf(NewPasswordScreenState())

    private fun setLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }

    fun isLoading() = state.isLoading

    fun onNewPasswordTextChanged(value: String) {
        state = state.copy(newPassword = value)
    }

    fun onRepeatPasswordTextChanged(value: String) {
        state = state.copy(repeatPassword = value)
    }

    fun onConfirmClick(navigateToLoginScreen: () -> Unit) {
        viewModelScope.launch {
            if (isNewPasswordValid())
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

    private fun isNewPasswordValid(): Boolean {
        if (!isRepeatPasswordsMatch()) {
            state = state.copy(
                repeatPasswordError = UiText.StringResource(R.string.passwords_dont_match)
            )
            return false
        }
        PasswordField(state.newPassword).validate().let {
            state = state.copy(newPasswordError = it.errorMessage)
            return it.successful
        }
    }

    private fun isRepeatPasswordsMatch() = state.newPassword == state.repeatPassword


    private val TAG = "TAGG"
}