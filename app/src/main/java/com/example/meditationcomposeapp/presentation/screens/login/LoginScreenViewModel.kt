package com.example.meditationcomposeapp.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.navigation.Screen

class LoginScreenViewModel constructor() : ViewModel() {

    private var _email = mutableStateOf("")

    val email: State<String> = _email

    fun setEmail(value: String) {
        _email.value = value
    }

    fun onLoginTextChanged(value: String) {
        setEmail(value)
    }

    private val _password = mutableStateOf("")

    val password: State<String> = _password

    fun setPassword(value: String) {
        _password.value = value
    }

    fun onPasswordTextChanged(value: String) {
        setPassword(value)
    }


    fun onForgotPasswordClicked(navController: NavController) {
        navController.navigate(Screen.RestorePassword.route)
    }

    fun onLoginClicked(navigateToMainScreen: () -> Unit) {
        val email = _email.value
        val password = _password.value

        //todo replace with real logic
        if (isValidLogin(email) || isValidPassword(password)) {
            //        loginUseCase(email, password)
            // on success
            navigateToMainScreen()
            //on error show pop-up
        }

    }

    fun onSignUpClicked(navigateToRegistrationScreen: () -> Unit) {
        navigateToRegistrationScreen()
    }

    private fun isValidLogin(login: String): Boolean {
        val regexp = Regex(LOGIN_REGEX)
        return regexp.matches(login)
    }

    private fun isValidPassword(password: String): Boolean {
        val regexp = Regex(PASSWORD_REGEX)
        return password.matches(regexp)
    }

    companion object {
        private const val LOGIN_REGEX = """[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+.+.[a-zA-Z]{2,4}"""
        private const val PASSWORD_REGEX = """^(?=.*[A-Z].*[A-Z])(?=.*[!@#${'$'}&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}${'$'}"""
    }
}