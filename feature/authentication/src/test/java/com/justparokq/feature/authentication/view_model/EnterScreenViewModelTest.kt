package com.justparokq.feature.authentication.view_model

import com.justparokq.feature.authentication.api.enter_screen.EnterScreenNavRoute
import com.justparokq.feature.authentication.api.enter_screen.EnterScreenViewModel
import com.justparokq.core.coroutines_test.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class EnterScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    private lateinit var viewModel: EnterScreenViewModel

    @Before
    fun setup() {
        viewModel = EnterScreenViewModel()
    }

    @Test
    fun `onEnterClick, navigate to login screen`() = runTest {
        viewModel.onEnterClick()

        val sharedFlowResult = mutableListOf<EnterScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            EnterScreenNavRoute.LoginScreen
        )
        job.cancel()
    }

    @Test
    fun `onDontHaveAccountClick, navigate to registration screen`() = runTest {
        viewModel.onDontHaveAccountClick()

        val sharedFlowResult = mutableListOf<EnterScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            EnterScreenNavRoute.RegistrationScreen
        )
        job.cancel()
    }
}