package com.example.feature.profile

import com.example.core.data_store.use_case.ClearAuthDataUseCase
import com.example.coroutines_test.CoroutinesTestRule
import com.example.feature.profile.api.ProfileScreenNavRoute
import com.example.feature.profile.api.ProfileScreenViewModel
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

        assert(viewModel.navigationEvent.value == ProfileScreenNavRoute.EnterScreen)
        verify(clearAuthDataUseCase).invoke()
    }

}