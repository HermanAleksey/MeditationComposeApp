package com.example.meditationcomposeapp.presentation.screens.restorepassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RestorePasswordScreenViewModel : ViewModel() {
    private val _firstNumber = mutableStateOf(EMPTY_NUMBER)
    val firstNumber: State<Int> = _firstNumber

    private val _secondNumber = mutableStateOf(EMPTY_NUMBER)
    val secondNumber: State<Int> = _secondNumber

    private val _thirdNumber = mutableStateOf(EMPTY_NUMBER)
    val thirdNumber: State<Int> = _thirdNumber

    private val _fourthNumber = mutableStateOf(EMPTY_NUMBER)
    val fourthNumber: State<Int> = _fourthNumber

    private val _fivesNumber = mutableStateOf(EMPTY_NUMBER)
    val fivesNumber: State<Int> = _fivesNumber

    private val _codeNumbers = arrayOf(
        _firstNumber,
        _secondNumber,
        _thirdNumber,
        _fourthNumber,
        _fivesNumber
    )

    fun getCode() = arrayOf(firstNumber, secondNumber, thirdNumber, fourthNumber, fivesNumber)


    fun setDigit(index: Int, value: Int) {
        _codeNumbers[index].value = value
    }

    fun onLastDigitFilled(navigateToNewPasswordScreen: () -> Unit) {
        navigateToNewPasswordScreen()
    }

    companion object {
        const val EMPTY_NUMBER = -1
    }
}