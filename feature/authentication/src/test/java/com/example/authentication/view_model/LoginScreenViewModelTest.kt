package com.example.authentication.view_model

import com.example.authentication.FakeObjects.getFakeProfile
import com.example.authentication.api.login_screen.LoginAction
import com.example.authentication.api.login_screen.LoginScreenNavRoute
import com.example.authentication.api.login_screen.LoginScreenViewModel
import com.example.core.authentication_source.api.use_case.LoginUseCase
import com.example.core.data_store.UserDataStore
import com.example.core.model.NetworkResponse
import com.example.coroutines_test.CoroutinesTestRule
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

        viewModel.processAction(LoginAction.LoginTextChanged(login))

        assert(viewModel.uiState.value.login == login)
    }

    @Test
    fun `onPasswordTextChanged, password changed`() {
        val pass = "qwerwe"

        viewModel.processAction(LoginAction.PasswordTextChanged(pass))

        assertEquals(viewModel.uiState.value.password, pass)
    }


    @Test
    fun `onSignUpClicked, navigation invoked`() = runTest {
        viewModel.processAction(LoginAction.SignUpClick)

        val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            LoginScreenNavRoute.RegistrationScreen
        )
        job.cancel()
    }

    @Test
    fun `onLoginClicked, login is not valid, pass valid, don't call loginUseCase`() = runTest {
        viewModel.processAction(LoginAction.LoginTextChanged(""))
        viewModel.processAction(LoginAction.PasswordTextChanged("qwdq"))

        viewModel.processAction(LoginAction.LoginClick)

        advanceUntilIdle()

        val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            null
        )
        job.cancel()
    }

    @Test
    fun `onLoginClicked, password is not valid, login valid, don't call loginUseCase`() = runTest {
        viewModel.processAction(LoginAction.LoginTextChanged("qwdq"))
        viewModel.processAction(LoginAction.PasswordTextChanged(""))

        viewModel.processAction(LoginAction.LoginClick)

        val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            null
        )
        job.cancel()
    }

    @Test
    fun `onLoginClicked, login and password valid, login success, navigate`() = runTest {
        val login = "qeww"
        val password = "qeww243"

        viewModel.processAction(LoginAction.LoginTextChanged(login))
        viewModel.processAction(LoginAction.PasswordTextChanged(password))
        whenever(loginUseCase(login, password))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Success(
                        data = getFakeProfile()
                    )
                )
            })

        viewModel.processAction(LoginAction.LoginClick)

        val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            LoginScreenNavRoute.MainScreen
        )
        job.cancel()
    }

    @Test
    fun `onLoginClicked, login and password valid, login error, don't navigate`() = runTest {
        val login = "qeww"
        val password = "qeww243"

        viewModel.processAction(LoginAction.LoginTextChanged(login))
        viewModel.processAction(LoginAction.PasswordTextChanged(password))
        whenever(loginUseCase(login, password))
            .thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        data = null,
                        error = null,
                    )
                )
            })

        viewModel.processAction(LoginAction.LoginClick)

        val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assert(sharedFlowResult.firstOrNull() == null)
        job.cancel()
    }

    @Test
    fun `onLoginClicked, login and password valid, login success, save credits into dataStore`() =
        runTest {
            val login = "qeww"
            val password = "qeww243"

            viewModel.processAction(LoginAction.LoginTextChanged(login))
            viewModel.processAction(LoginAction.PasswordTextChanged(password))
            whenever(loginUseCase(login, password))
                .thenReturn(flow {
                    emit(
                        NetworkResponse.Success(
                            data = getFakeProfile()
                        )
                    )
                })

            viewModel.processAction(LoginAction.LoginClick)

            val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            assertEquals(
                sharedFlowResult.firstOrNull(),
                LoginScreenNavRoute.MainScreen
            )
            verify(userDataStore).writeLogin(login)
            verify(userDataStore).writePassword(password)
            job.cancel()
        }

    @Test
    fun `onForgotPasswordClicked, navigate to EnterLoginScreen`() = runTest {
        val login = "wefwefwe"

        viewModel.processAction(LoginAction.LoginTextChanged(login))
        viewModel.processAction(LoginAction.ForgotPasswordClick)

        val sharedFlowResult = mutableListOf<LoginScreenNavRoute?>()
        val job = launch {
            viewModel.navigationEvent.toList(sharedFlowResult)
        }
        advanceUntilIdle()

        assertEquals(
            sharedFlowResult.firstOrNull(),
            LoginScreenNavRoute.EnterLoginScreen(login)
        )
        job.cancel()
    }

    @Test
    fun `onPasswordTextChanged, uiState update password`() = runTest {
        val password = "wefwefwe"

        viewModel.processAction(LoginAction.PasswordTextChanged(password))

        advanceUntilIdle()

        assertEquals(password, viewModel.uiState.value.password)
    }

    @Test
    fun `onLoginTextChanged, uiState update login`() {
        val login = "wefwefwe"

        viewModel.processAction(LoginAction.LoginTextChanged(login))

        assertEquals(login, viewModel.uiState.value.login)
    }
}