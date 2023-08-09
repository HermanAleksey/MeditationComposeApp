package com.example.authentication.view_model

import com.example.authentication.api.enter_login_screen.EnterLoginAction
import com.example.authentication.api.enter_login_screen.EnterLoginScreenNavRoute
import com.example.authentication.api.enter_login_screen.EnterLoginScreenViewModel
import com.justparokq.core.authentication_source.api.use_case.RequestPasswordRestorationUseCase
import com.justparokq.core.model.NetworkResponse
import com.example.coroutines_test.CoroutinesTestRule
import com.example.network.SuccessInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class EnterLoginScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var requestPasswordRestorationUseCase: RequestPasswordRestorationUseCase

    private lateinit var viewModel: EnterLoginScreenViewModel

    @Before
    fun setup() {
        viewModel = EnterLoginScreenViewModel(requestPasswordRestorationUseCase)
    }

    @Test
    fun `init, default values check`() {
        assertTrue(!viewModel.uiState.value.isLoading)
        assertEquals(viewModel.uiState.value.login, "")
        assertEquals(viewModel.uiState.value.loginError, null)
    }

    @Test
    fun `onLoginTextChanged, update login`() {
        val login = "qweqwe"

        viewModel.processAction(EnterLoginAction.LoginTextChanged(login))

        assert(viewModel.uiState.value.login == login)
    }

    @Test
    fun `onConfirmClick, email is valid, network response error, don't navigate`() = runTest {
        val login = "qwewqe"
        whenever(requestPasswordRestorationUseCase(anyString()))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Success(
                        SuccessInfo(
                            success = false,
                            message = null
                        )
                    )
                )
            })
        viewModel.processAction(EnterLoginAction.LoginTextChanged(login))

        viewModel.processAction(EnterLoginAction.ConfirmClick)

        val sharedFlowResult = mutableListOf<EnterLoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assert(sharedFlowResult.firstOrNull() == null)
        verify(requestPasswordRestorationUseCase).invoke(login)
        job.cancel()
    }

    @Test
    fun `onConfirmClick, email is valid, network response success, navigate`() = runTest {
        val login = "qwewqe"
        whenever(requestPasswordRestorationUseCase(anyString()))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Success(
                        SuccessInfo(
                            success = true,
                            message = null
                        )
                    )
                )
            })
        viewModel.processAction(EnterLoginAction.LoginTextChanged(login))

        viewModel.processAction(EnterLoginAction.ConfirmClick)

        val sharedFlowResult = mutableListOf<EnterLoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.first(),
            EnterLoginScreenNavRoute.EnterCodeScreen(login)
        )
        verify(requestPasswordRestorationUseCase).invoke(login)
        job.cancel()
    }

    @Test
    fun `onConfirmClick, email is not valid, network response error, don't navigate`() = runTest {
        val login = "qwewqe"
        whenever(requestPasswordRestorationUseCase(anyString()))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        null, "error"
                    )
                )
            })
        viewModel.processAction(EnterLoginAction.LoginTextChanged(login))

        viewModel.processAction(EnterLoginAction.ConfirmClick)

        val sharedFlowResult = mutableListOf<EnterLoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assert(sharedFlowResult.firstOrNull() == null)
        verify(requestPasswordRestorationUseCase).invoke(login)
        job.cancel()
    }

}