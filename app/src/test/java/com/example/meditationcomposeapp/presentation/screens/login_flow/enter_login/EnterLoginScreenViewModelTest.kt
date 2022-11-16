package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_login

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
import com.example.meditationcomposeapp.model.usecase.authentication.RequestPasswordRestorationUseCase
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
    fun `onConfirmClick, email is not valid, do nothing`() {
        var navigate = false

        viewModel.onConfirmClick { navigate = true }

        assert(!navigate)
    }

    @Test
    fun `onConfirmClick, email is valid, network response error, don't navigate`() = runTest{
        var navigate = false
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

        viewModel.onConfirmClick { navigate = true }

        advanceUntilIdle()

        assert(!navigate)
        verify(requestPasswordRestorationUseCase).invoke(login)
    }

    @Test
    fun `onConfirmClick, email is valid, network response success, navigate`() = runTest{
        var navigate = false
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

        viewModel.onConfirmClick { navigate = true }

        advanceUntilIdle()

        assert(navigate)
        verify(requestPasswordRestorationUseCase).invoke(login)
    }
}