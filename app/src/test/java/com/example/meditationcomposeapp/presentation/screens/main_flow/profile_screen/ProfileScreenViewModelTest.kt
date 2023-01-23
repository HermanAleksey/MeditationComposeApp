package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.usecase.authentication.ClearAuthDataUseCase
import com.example.meditationcomposeapp.presentation.navigation.NavigationEvent
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.SplashScreenDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ProfileScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var clearAuthDataUseCase: ClearAuthDataUseCase

    private lateinit var viewModel: ProfileScreenViewModel

    @Before
    fun setup() {
        viewModel = ProfileScreenViewModel(clearAuthDataUseCase)
    }

    @Test
    fun `onBeerItemClicked, navigate`() = runTest{
        viewModel.onLogOutClicked()

        advanceUntilIdle()

        assertEquals(
            NavigationEvent.NavigateWithPop(
                direction = EnterScreenDestination(),
                popUpTo = SplashScreenDestination(),
                inclusive = false
            ).toString(),
            viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
        )
        verify(clearAuthDataUseCase).invoke()
    }

}