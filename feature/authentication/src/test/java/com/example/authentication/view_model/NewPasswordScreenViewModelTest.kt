package com.example.authentication.view_model

import com.example.authentication.api.new_password_screen.NewPasswordScreenNavRoute
import com.example.authentication.api.new_password_screen.NewPasswordScreenViewModel
import com.example.authentication.internal.screens.new_password.NewPasswordAction
import com.example.core.authentication_source.api.use_case.SetNewPasswordUseCase
import com.example.core.model.NetworkResponse
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
        viewModel.onNewPasswordTextChanged("")

        viewModel.processAction(NewPasswordAction.ConfirmClick)

        val sharedFlowResult = mutableListOf<NewPasswordScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            null
        )
        job.cancel()
        verify(setNewPasswordUseCase, never()).invoke(anyString(), anyString())
    }

    @Test
    fun `onConfirmClick, new password is valid, passwords dont match, dont call request`() =
        runTest {
            viewModel.onNewPasswordTextChanged("wdwddw")
            viewModel.onRepeatPasswordTextChanged("212")

            viewModel.processAction(NewPasswordAction.ConfirmClick)

            val sharedFlowResult = mutableListOf<NewPasswordScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            verify(setNewPasswordUseCase, never()).invoke(anyString(), anyString())
            job.cancel()
        }

    @Test
    fun `onConfirmClick, new password is valid, request fail, don't navigate`() = runTest {
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

        viewModel.processAction(NewPasswordAction.ConfirmClick)

        val sharedFlowResult = mutableListOf<NewPasswordScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            null
        )
        job.cancel()
        verify(setNewPasswordUseCase).invoke(login, password)
    }

    @Test
    fun `onConfirmClick, new password is valid, request success, response data error, don't navigate`() =
        runTest {
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

            viewModel.processAction(NewPasswordAction.ConfirmClick)

            val sharedFlowResult = mutableListOf<NewPasswordScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()
            verify(setNewPasswordUseCase).invoke(login, password)
        }

    @Test
    fun `onConfirmClick, new password is valid, request success, response success, navigate`() =
        runTest {
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

            viewModel.processAction(NewPasswordAction.ConfirmClick)

            val sharedFlowResult = mutableListOf<NewPasswordScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                NewPasswordScreenNavRoute.LoginScreen
            )
            job.cancel()
            verify(setNewPasswordUseCase).invoke(login, password)
        }
}