package com.example.authentication.view_model

import com.example.authentication.api.registration_screen.RegistrationScreenNavRoute
import com.example.authentication.api.registration_screen.RegistrationScreenViewModel
import com.example.authentication.internal.screens.enter_login.EnterLoginAction
import com.example.authentication.internal.screens.registration.RegistrationAction
import com.example.core.authentication_source.api.use_case.RegisterUseCase
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
class RegistrationScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var registerUseCase: RegisterUseCase

    private lateinit var viewModel: RegistrationScreenViewModel

    @Before
    fun setup() {
        viewModel = RegistrationScreenViewModel(registerUseCase)
    }

    @Test
    fun `init, default values check`() {
        assert(!viewModel.uiState.value.isLoading)
        assert(viewModel.uiState.value.login == "")
        assert(viewModel.uiState.value.password == "")
        assert(viewModel.uiState.value.name == "")
        assert(viewModel.uiState.value.loginError == null)
        assert(viewModel.uiState.value.passwordError == null)
        assert(viewModel.uiState.value.nameError == null)
    }

    @Test
    fun `onNameTextChanged, name changed`() {
        val name = "wefwe"

        viewModel.processAction(RegistrationAction.NameTextChanged(name))

        assert(viewModel.uiState.value.name == name)
    }

    @Test
    fun `onLoginTextChanged, name changed`() {
        val login = "wefwe"

        viewModel.processAction(EnterLoginAction.LoginTextChanged(login))

        assert(viewModel.uiState.value.login == login)
    }

    @Test
    fun `onPasswordTextChanged, name changed`() {
        val password = "wefwe"

        viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

        assert(viewModel.uiState.value.password == password)
    }

    @Test
    fun `onSignInClicked, navigate to login screen`() = runTest {
        viewModel.processAction(RegistrationAction.SignInClick)

        val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            RegistrationScreenNavRoute.LoginScreen
        )
        job.cancel()
    }

    @Test
    fun `onSignUpClicked, name not valid, login not valid, password not valid, don't call request`() =
        runTest {
            val login = ""
            val password = ""
            val name = ""
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()
            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, name is valid, login not valid, password not valid, don't call request`() =
        runTest {
            val login = "qwewqe"
            val password = ""
            val name = ""
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()
            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, name not valid, login is valid, password not valid, don't call request`() =
        runTest {
            val login = ""
            val password = "qweqwe"
            val name = ""
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, name not valid, login not valid, password is valid, don't call request`() =
        runTest {
            val login = ""
            val password = ""
            val name = "qwdqwd"
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, name is valid, login is valid, password not valid, don't call request`() =
        runTest {
            val login = "qwewqe"
            val password = "qwewqe"
            val name = ""
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()
            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, name is valid, login not valid, password is valid, don't call request`() =
        runTest {
            val login = "qwewqe"
            val password = ""
            val name = "breerbe"
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, name not valid, login is valid, password is valid, don't call request`() =
        runTest {
            val login = ""
            val password = "btrtnre"
            val name = "dfccnc"
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()
            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
        }

    @Test
    fun `onSignUpClicked, fields are valid, register fail, don't navigate`() =
        runTest {
            val login = "qwewqe"
            val password = "yumtymf"
            val name = "fghmfmy"
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))
            whenever(registerUseCase(name, login, password))
                .thenReturn(
                    flow {
                        emit(NetworkResponse.Failure(null, null))
                    }
                )

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()
            verify(registerUseCase).invoke(name, login, password)
        }

    @Test
    fun `onSignUpClicked, fields are valid, register success, response error, don't navigate`() =
        runTest {
            val login = "qwewqe"
            val password = "yumtymf"
            val name = "fghmfmy"
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))
            whenever(registerUseCase(name, login, password))
                .thenReturn(
                    flow {
                        emit(
                            NetworkResponse.Success(
                                data = SuccessInfo(
                                    false, null
                                )
                            )
                        )
                    }
                )

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                null
            )
            job.cancel()

            verify(registerUseCase).invoke(name, login, password)
        }

    @Test
    fun `onSignUpClicked, fields are valid, register success, response success, navigate`() =
        runTest {
            val login = "qwewqe"
            val password = "yumtymf"
            val name = "fghmfmy"
            viewModel.processAction(RegistrationAction.NameTextChanged(name))
            viewModel.processAction(EnterLoginAction.LoginTextChanged(login))
            viewModel.processAction(RegistrationAction.PasswordTextChanged(password))
            whenever(registerUseCase(name, login, password))
                .thenReturn(
                    flow {
                        emit(
                            NetworkResponse.Success(
                                data = SuccessInfo(
                                    true, null
                                )
                            )
                        )
                    }
                )

            viewModel.processAction(RegistrationAction.SignUpClick)

            val sharedFlowResult = mutableListOf<RegistrationScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                RegistrationScreenNavRoute.LoginScreen
            )
            job.cancel()
            verify(registerUseCase).invoke(name, login, password)
        }
}
