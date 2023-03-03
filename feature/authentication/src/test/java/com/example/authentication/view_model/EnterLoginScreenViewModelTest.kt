package com.example.authentication.view_model

import com.example.authentication.api.enter_login_screen.EnterLoginScreenNavRoute
import com.example.authentication.api.enter_login_screen.EnterLoginScreenViewModel
import com.example.core.authentication_source.api.use_case.RequestPasswordRestorationUseCase
import com.example.core.model.NetworkResponse
import com.example.coroutines_test.CoroutinesTestRule
import com.example.network.SuccessInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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

        viewModel.onLoginTextChanged(login)

        assert(viewModel.uiState.value.login == login)
    }

    @Test
    fun `onConfirmClick, email is valid, network response error, don't navigate`() = runTest{
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
        viewModel.onLoginTextChanged(login)

        viewModel.onConfirmClick()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == null)
        verify(requestPasswordRestorationUseCase).invoke(login)
    }

    @Test
    fun `onConfirmClick, email is valid, network response success, navigate`() = runTest{
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
        viewModel.onLoginTextChanged(login)

        viewModel.onConfirmClick()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == EnterLoginScreenNavRoute.EnterCodeScreen(login))
        verify(requestPasswordRestorationUseCase).invoke(login)
    }

    @Test
    fun `onConfirmClick, email is not valid, network response error, don't navigate`() = runTest{
        val login = "qwewqe"
        whenever(requestPasswordRestorationUseCase(anyString()))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        null, "error"
                    )
                )
            })
        viewModel.onLoginTextChanged(login)

        viewModel.onConfirmClick()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == null)
        verify(requestPasswordRestorationUseCase).invoke(login)
    }

}