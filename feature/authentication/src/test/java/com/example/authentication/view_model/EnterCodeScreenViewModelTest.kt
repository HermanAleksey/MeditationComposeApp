package com.example.authentication.view_model

import com.example.authentication.api.enter_code_screen.EnterCodeScreenNavRoute
import com.example.authentication.api.enter_code_screen.EnterCodeScreenState
import com.example.authentication.api.enter_code_screen.EnterCodeScreenViewModel
import com.example.core.authentication_source.api.use_case.VerifyCodeUseCase
import com.example.core.model.NetworkResponse
import com.example.coroutines_test.CoroutinesTestRule
import com.example.network.SuccessInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
import java.util.*


@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class EnterCodeScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var verifyCodeUseCase: VerifyCodeUseCase

    private lateinit var viewModel: EnterCodeScreenViewModel

    @Before
    fun setup() {
        viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
    }

    @Test
    fun `init, default values check`() {
        val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)

        assert(viewModel.uiState.value.code.contentEquals(arrayOf(-1, -1, -1, -1, -1)))
        assertEquals(viewModel.uiState.value.isLoading, false)
    }

    @Test
    fun `onCodeDigitChanged, input 5 numbers, code fully inputted`() {
        val uiState = EnterCodeScreenState()
        uiState.code[0] = 1
        uiState.code[1] = 1
        uiState.code[2] = 1
        uiState.code[3] = 1
        uiState.code[4] = 1

        viewModel.onCodeDigitChanged(0, 1)
        viewModel.onCodeDigitChanged(1, 1)
        viewModel.onCodeDigitChanged(2, 1)
        viewModel.onCodeDigitChanged(3, 1)
        val resultSuccessfully = viewModel.onCodeDigitChanged(4, 1)

        assert(viewModel.uiState.value.code.contentToString() == uiState.code.contentToString())
        assert(resultSuccessfully)
    }

    @Test
    fun `onCodeDigitChanged, set first item eq 8, code not fully inputted`() {
        val index = 0
        val value = 8
        val uiState = EnterCodeScreenState()
        uiState.code[index] = value

        val resultSuccessfully = viewModel.onCodeDigitChanged(index, value)

        assert(viewModel.uiState.value.code.contentToString() == uiState.code.contentToString())
        assert(!resultSuccessfully)
    }

    @Test
    fun `onCodeDigitChanged, set item with index -1 eq 8, don't change value, code not fully inputted`() {
        val index = -1
        val value = 8
        val uiState = EnterCodeScreenState()

        val resultSuccessfully = viewModel.onCodeDigitChanged(index, value)

        assert(viewModel.uiState.value.code.contentToString() == uiState.code.contentToString())
        assert(!resultSuccessfully)
    }

    @Test
    fun `onCodeDigitChanged, set item with index 6 (out of bound), don't change value, code not fully inputted`() {
        val index = 6
        val value = 8
        val uiState = EnterCodeScreenState()

        val resultSuccessfully = viewModel.onCodeDigitChanged(index, value)

        assert(viewModel.uiState.value.code.contentToString() == uiState.code.contentToString())
        assert(!resultSuccessfully)
    }

    @Test
    fun `onLastDigitFilled, call with inputted code`() = runTest {
        val login = "q"
        val codeAsString = "12341"
        viewModel.onCodeDigitChanged(0, 1)
        viewModel.onCodeDigitChanged(1, 2)
        viewModel.onCodeDigitChanged(2, 3)
        viewModel.onCodeDigitChanged(3, 4)
        viewModel.onCodeDigitChanged(4, 1)

        whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
            flow {
                emit(NetworkResponse.Failure(error = null))
            }
        )

        viewModel.onLastDigitFilled(login)

        advanceUntilIdle()

        verify(verifyCodeUseCase).invoke(login, codeAsString)
    }

    @Test
    fun `onLastDigitFilled, process loading state false, uiState is not loading`() = runTest {
        val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
        val login = "q"

        whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
            flow {
                emit(NetworkResponse.Loading(false))
            }
        )

        viewModel.onLastDigitFilled(login)

        advanceUntilIdle()

        assertTrue(!viewModel.uiState.value.isLoading)
    }

    @Test
    fun `onLastDigitFilled, process loading state true, uiState is loading`() =
        runTest(UnconfinedTestDispatcher()) {
            val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
            val login = "q"

            whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
                flow {
                    emit(NetworkResponse.Loading(true))
                }
            )

            viewModel.onLastDigitFilled(login)

            val sharedFlowResult = mutableListOf<EnterCodeScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertTrue(viewModel.uiState.value.isLoading)
            assertTrue(sharedFlowResult.lastOrNull() == null)
            job.cancel()
        }

    @Test
    fun `onLastDigitFilled, verify code request success, answer success, navigate to NewPasswordScreen`() =
        runTest {
            val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
            val login = "hihiq"

            whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
                flow {
                    emit(NetworkResponse.Success(SuccessInfo(true, null)))
                }
            )

            viewModel.onLastDigitFilled(login)

            val sharedFlowResult = mutableListOf<EnterCodeScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                EnterCodeScreenNavRoute.NewPasswordScreen(
                    login
                )
            )
            job.cancel()
        }

    @Test
    fun `onLastDigitFilled, verify code request success, answer error, don't navigate to NewPasswordScreen`() =
        runTest {
            val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
            val login = "hihiq"

            whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
                flow {
                    emit(NetworkResponse.Success(SuccessInfo(false, "wfe")))
                }
            )

            viewModel.onLastDigitFilled(login)

            val sharedFlowResult = mutableListOf<EnterCodeScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertTrue(sharedFlowResult.lastOrNull() == null)
            job.cancel()
        }

    @Test
    fun `onLastDigitFilled, verify code request fail, don't navigate to NewPasswordScreen`() =
        runTest {
            val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
            val login = "hihiq"

            whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
                flow {
                    emit(NetworkResponse.Failure(null, "error"))
                }
            )

            viewModel.onLastDigitFilled(login)

            val sharedFlowResult = mutableListOf<EnterCodeScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertTrue(sharedFlowResult.lastOrNull() == null)
            job.cancel()
        }

    @Test
    fun `onLastDigitFilled, network request success, error received, clear code, don't navigate`() =
        runTest {
            val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
            val login = "q"

            whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
                flow {
                    emit(NetworkResponse.Success(data = SuccessInfo(false, null)))
                }
            )

            viewModel.onLastDigitFilled(login)

            val sharedFlowResult = mutableListOf<EnterCodeScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertTrue(sharedFlowResult.lastOrNull() == null)
            assertEquals(
                viewModel.uiState.value.code.contentToString(),
                EnterCodeScreenState.EMPTY_CODE_VALUE.contentToString()
            )
            job.cancel()
        }
}