package com.example.meditationcomposeapp.presentation.screens.login_flow.login

import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.FakeObjects.getFakeProfile
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

    @Mock
    private lateinit var userDataStore: UserDataStore

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var viewModel: LoginScreenViewModel

    @Before
    fun setup() {
        viewModel = LoginScreenViewModel(userDataStore, loginUseCase)
    }

    @Test
    fun `init, default values check`() {
        assert(!viewModel.uiState.value.isLoading)
        assert(viewModel.uiState.value.login == "")
        assert(viewModel.uiState.value.password == "")
        assert(viewModel.uiState.value.loginError == null)
        assert(viewModel.uiState.value.passwordError == null)
    }

    @Test
    fun `onLoginTextChanged, login changed`() {
        val login = "qwerwe"

        viewModel.onLoginTextChanged(login)

        assert(viewModel.uiState.value.login == login)
    }

    @Test
    fun `onPasswordTextChanged, password changed`() {
        val pass = "qwerwe"

        viewModel.onPasswordTextChanged(pass)

        assert(viewModel.uiState.value.password == pass)
    }

    @Test
    fun `onForgotPasswordClicked, navigation invoked`() {
        var navigated = false

        viewModel.onForgotPasswordClicked { navigated = true }

        assert(navigated)
    }

    @Test
    fun `onSignUpClicked, navigation invoked`() {
        var navigated = false

        viewModel.onSignUpClicked { navigated = true }

        assert(navigated)
    }

    @Test
    fun `onLoginClicked, login is not valid, pass valid, don't call loginUseCase`() = runTest {
        var navigated = false
        viewModel.onLoginTextChanged("")
        viewModel.onPasswordTextChanged("qwewqeq")

        viewModel.onLoginClicked { navigated = true }

        advanceUntilIdle()

        assert(!navigated)
    }

    @Test
    fun `onLoginClicked, password is not valid, login valid, don't call loginUseCase`() = runTest {
        var navigated = false
        viewModel.onLoginTextChanged("wefwefwef")
        viewModel.onPasswordTextChanged("")

        viewModel.onLoginClicked { navigated = true }

        advanceUntilIdle()

        assert(!navigated)
    }

    @Test
    fun `onLoginClicked, login and password valid, login success, navigate`() = runTest {
        var navigated = false
        val login = "qeww"
        val password = "qeww243"

        viewModel.onLoginTextChanged(login)
        viewModel.onPasswordTextChanged(password)
        whenever(loginUseCase(login, password))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Success(
                        data = getFakeProfile()
                    )
                )
            })

        viewModel.onLoginClicked { navigated = true }

        advanceUntilIdle()

        assert(navigated)
    }

    @Test
    fun `onLoginClicked, login and password valid, login error, don't navigate`() = runTest {
        var navigated = false
        val login = "qeww"
        val password = "qeww243"

        viewModel.onLoginTextChanged(login)
        viewModel.onPasswordTextChanged(password)
        whenever(loginUseCase(login, password))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        data = null,
                        error = null,
                    )
                )
            })

        viewModel.onLoginClicked { navigated = true }

        advanceUntilIdle()

        assert(!navigated)
    }

    @Test
    fun `onLoginClicked, login and password valid, login success, save credits into dataStore`() = runTest {
        var navigated = false
        val login = "qeww"
        val password = "qeww243"

        viewModel.onLoginTextChanged(login)
        viewModel.onPasswordTextChanged(password)
        whenever(loginUseCase(login, password))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Success(
                        data = getFakeProfile()
                    )
                )
            })

        viewModel.onLoginClicked { navigated = true }

        advanceUntilIdle()

        assert(navigated)
        verify(userDataStore).writeLogin(login)
        verify(userDataStore).writePassword(password)
    }
}