package com.example.authentication.view_model

import com.example.authentication.api.enter_screen.EnterScreenNavRoute
import com.example.authentication.api.enter_screen.EnterScreenViewModel
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
    fun `onEnterClick, navigate to login screen`() {
        viewModel.onEnterClick()

        assert(viewModel.navigationEvent.value == EnterScreenNavRoute.LoginScreen)
    }

    @Test
    fun `onDontHaveAccountClick, navigate to registration screen`()  {
        viewModel.onDontHaveAccountClick()

        assert(viewModel.navigationEvent.value == EnterScreenNavRoute.RegistrationScreen)
    }
}