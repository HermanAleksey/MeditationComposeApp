package com.example.meditationcomposeapp.presentation.screens.login_flow.enter

import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.LoginScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.RegistrationScreenDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

        assertEquals(
            NavigationEvent.Navigate(
                LoginScreenDestination()
            ).toString(),
            viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
        )
    }

    @Test
    fun `onDontHaveAccountClick, navigate to registration screen`()  {
        viewModel.onDontHaveAccountClick()

        assertEquals(
            NavigationEvent.Navigate(
                RegistrationScreenDestination()
            ).toString(),
            viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
        )
    }
}