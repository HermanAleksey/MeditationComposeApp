package com.example.meditationcomposeapp.presentation.screens.login_flow.enter_code

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
import com.example.meditationcomposeapp.model.usecase.authentication.VerifyCodeUseCase
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
    fun `onCodeDigitChanged, input 5 numbers, code fully inputed`() {
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
    fun `onLastDigitFilled, process loading state true, uiState is loading`() = runTest {
        val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
        val login = "q"

        whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
            flow {
                emit(NetworkResponse.Loading(true))
            }
        )

        viewModel.onLastDigitFilled(login)

        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `onLastDigitFilled, network request success, error received, clear code`() = runTest {
        val viewModel = EnterCodeScreenViewModel(verifyCodeUseCase)
        val login = "q"

        whenever(verifyCodeUseCase(anyString(), anyString())).thenReturn(
            flow {
                emit(NetworkResponse.Success(data = SuccessInfo(false, null)))
            }
        )

        viewModel.onLastDigitFilled(login)

        advanceUntilIdle()

        assert(viewModel.uiState.value.code.contentToString() == EnterCodeScreenState.EMPTY_CODE_VALUE.contentToString())
    }
}