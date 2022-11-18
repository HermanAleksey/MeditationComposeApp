package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EnterScreenViewModelTest {

    private lateinit var viewModel: EnterScreenViewModel

    @Before
    fun setup() {
        viewModel = EnterScreenViewModel()
    }

    @Test
    fun `onEnterClick, navigate to login screen`(){
        var navigateToEnterScreen = false

        viewModel.onEnterClick { navigateToEnterScreen = true }

        assert(navigateToEnterScreen)
    }

    @Test
    fun `onDontHaveAccountClick, navigate to registration screen`(){
        var navigateToRegisterScreen = false

        viewModel.onEnterClick { navigateToRegisterScreen = true }

        assert(navigateToRegisterScreen)
    }
}