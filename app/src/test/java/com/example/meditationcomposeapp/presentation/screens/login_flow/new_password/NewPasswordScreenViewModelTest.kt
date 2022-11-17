package com.example.meditationcomposeapp.presentation.screens.login_flow.new_password

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
import com.example.meditationcomposeapp.model.usecase.authentication.SetNewPasswordUseCase
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
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class NewPasswordScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var setNewPasswordUseCase: SetNewPasswordUseCase

    private lateinit var viewModel: NewPasswordScreenViewModel

    @Before
    fun setup() {
        viewModel = NewPasswordScreenViewModel(setNewPasswordUseCase)
    }

    @Test
    fun `init, default values check`() {
        assert(!viewModel.uiState.value.isLoading)
        assert(viewModel.uiState.value.newPassword == "")
        assert(viewModel.uiState.value.repeatPassword == "")
        assert(viewModel.uiState.value.newPasswordError == null)
        assert(viewModel.uiState.value.repeatPasswordError == null)
    }

    @Test
    fun `onNewPasswordTextChanged, password is set`() {
        val newPass = "qww"

        viewModel.onNewPasswordTextChanged(newPass)

        assert(viewModel.uiState.value.newPassword == newPass)
    }

    @Test
    fun `onRepeatPasswordTextChanged, rep password is set`() {
        val repPass = "qww"

        viewModel.onRepeatPasswordTextChanged(repPass)

        assert(viewModel.uiState.value.repeatPassword == repPass)
    }

    @Test
    fun `onConfirmClick, new password is not valid, don't navigate`() = runTest {
        var navigated = false
        val login = "lo5gin"
        viewModel.onNewPasswordTextChanged("")

        viewModel.onConfirmClick(login) { navigated = true }

        advanceUntilIdle()

        assert(!navigated)
        verify(setNewPasswordUseCase, never()).invoke(anyString(), anyString())
    }

    @Test
    fun `onConfirmClick, new password is valid, passwords dont match, dont call request`() =
        runTest {
            var navigated = false
            val login = "lo4gin"
            viewModel.onNewPasswordTextChanged("wdwddw")
            viewModel.onRepeatPasswordTextChanged("212")

            viewModel.onConfirmClick(login) { navigated = true }

            advanceUntilIdle()

            assert(!navigated)
            verify(setNewPasswordUseCase, never()).invoke(anyString(), anyString())
        }

    @Test
    fun `onConfirmClick, new password is valid, request fail, don't navigate`() = runTest {
        var navigated = false
        val login = "logi4n"
        val password = "password"
        viewModel.onNewPasswordTextChanged(password)
        viewModel.onRepeatPasswordTextChanged(password)
        whenever(setNewPasswordUseCase(anyString(), anyString()))
            .thenReturn(
                flow {
                    emit(
                        NetworkResponse.Failure(
                            null, null
                        )
                    )
                }
            )

        viewModel.onConfirmClick (login) { navigated = true }

        advanceUntilIdle()

        assert(!navigated)
        verify(setNewPasswordUseCase).invoke(login, password)
    }

    @Test
    fun `onConfirmClick, new password is valid, request success, response data error, don't navigate`() =
        runTest {
            var navigated = false
            val login = "qweeqw"
            val password = "password"
            viewModel.onNewPasswordTextChanged(password)
            viewModel.onRepeatPasswordTextChanged(password)
            whenever(setNewPasswordUseCase(anyString(), anyString()))
                .thenReturn(
                    flow {
                        emit(
                            NetworkResponse.Success(
                                SuccessInfo(
                                    false, null
                                )
                            )
                        )
                    }
                )

            viewModel.onConfirmClick(login) { navigated = true }

            advanceUntilIdle()

            assert(!navigated)
            verify(setNewPasswordUseCase).invoke(login, password)
        }

    @Test
    fun `onConfirmClick, new password is valid, request success, response success, don't navigate`() =
        runTest {
            var navigated = false
            val login = "login"
            val password = "rebrtjn"
            viewModel.onNewPasswordTextChanged(password)
            viewModel.onRepeatPasswordTextChanged(password)
            whenever(setNewPasswordUseCase(anyString(), anyString()))
                .thenReturn(
                    flow {
                        emit(
                            NetworkResponse.Success(
                                SuccessInfo(
                                    true, null
                                )
                            )
                        )
                    }
                )

            viewModel.onConfirmClick(login) { navigated = true }

            advanceUntilIdle()

            assert(navigated)
            verify(setNewPasswordUseCase).invoke(login, password)
        }
}