package com.example.meditationcomposeapp.presentation.screens.login_flow.registration

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.SuccessInfo
import com.example.meditationcomposeapp.model.usecase.authentication.RegisterUseCase
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

        viewModel.onNameTextChanged(name)

        assert(viewModel.uiState.value.name == name)
    }

    @Test
    fun `onLoginTextChanged, name changed`() {
        val login = "wefwe"

        viewModel.onLoginTextChanged(login)

        assert(viewModel.uiState.value.login == login)
    }

    @Test
    fun `onPasswordTextChanged, name changed`() {
        val password = "wefwe"

        viewModel.onPasswordTextChanged(password)

        assert(viewModel.uiState.value.password == password)
    }

    @Test
    fun `onSignInClicked, navigate to login screen`() {
        var navigated = false

        viewModel.onSignInClicked { navigated = true }

        assert(navigated)
    }

    @Test
    fun `onSignUpClicked, name not valid, login not valid, password not valid, don't call request`() =
        runTest {
            var navigated = false
            val login = ""
            val password = ""
            val name = ""
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, name is valid, login not valid, password not valid, don't call request`() =
        runTest {
            var navigated = false
            val login = "qwewqe"
            val password = ""
            val name = ""
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, name not valid, login is valid, password not valid, don't call request`() =
        runTest {
            var navigated = false
            val login = ""
            val password = "qweqwe"
            val name = ""
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, name not valid, login not valid, password is valid, don't call request`() =
        runTest {
            var navigated = false
            val login = ""
            val password = ""
            val name = "qwdqwd"
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, name is valid, login is valid, password not valid, don't call request`() =
        runTest {
            var navigated = false
            val login = "qwewqe"
            val password = "qwewqe"
            val name = ""
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, name is valid, login not valid, password is valid, don't call request`() =
        runTest {
            var navigated = false
            val login = "qwewqe"
            val password = ""
            val name = "breerbe"
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, name not valid, login is valid, password is valid, don't call request`() =
        runTest {
            var navigated = false
            val login = ""
            val password = "btrtnre"
            val name = "dfccnc"
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase, never()).invoke(anyString(), anyString(), anyString())
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, fields are valid, register fail, don't navigate`() =
        runTest {
            var navigated = false
            val login = "qwewqe"
            val password = "yumtymf"
            val name = "fghmfmy"
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)
            whenever(registerUseCase(name, login, password))
                .thenReturn(
                    flow {
                        emit(NetworkResponse.Failure(null, null))
                    }
                )

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase).invoke(name, login, password)
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, fields are valid, register success, response error, don't navigate`() =
        runTest {
            var navigated = false
            val login = "qwewqe"
            val password = "yumtymf"
            val name = "fghmfmy"
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)
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

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase).invoke(name, login, password)
            assert(!navigated)
        }

    @Test
    fun `onSignUpClicked, fields are valid, register success, response success, don't navigate`() =
        runTest {
            var navigated = false
            val login = "qwewqe"
            val password = "yumtymf"
            val name = "fghmfmy"
            viewModel.onNameTextChanged(name)
            viewModel.onLoginTextChanged(login)
            viewModel.onPasswordTextChanged(password)
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

            viewModel.onSignUpClicked { navigated = true }

            advanceUntilIdle()

            verify(registerUseCase).invoke(name, login, password)
            assert(navigated)
        }
}
