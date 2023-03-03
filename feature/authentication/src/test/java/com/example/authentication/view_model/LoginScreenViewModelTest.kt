package com.example.authentication.view_model

import com.example.authentication.FakeObjects.getFakeProfile
import com.example.authentication.api.login_screen.LoginScreenNavRoute
import com.example.authentication.api.login_screen.LoginScreenViewModel
import com.example.core.authentication_source.api.use_case.LoginUseCase
import com.example.core.data_store.UserDataStore
import com.example.core.model.NetworkResponse
import com.example.coroutines_test.CoroutinesTestRule
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
    fun `onSignUpClicked, navigation invoked`() {
        viewModel.onSignUpClicked()

        assert(viewModel.navigationEvent.value == LoginScreenNavRoute.RegistrationScreen)
    }

    @Test
    fun `onLoginClicked, login is not valid, pass valid, don't call loginUseCase`() = runTest {
        viewModel.onLoginTextChanged("")
        viewModel.onPasswordTextChanged("qwewqeq")

        viewModel.onLoginClicked()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == null)
    }

    @Test
    fun `onLoginClicked, password is not valid, login valid, don't call loginUseCase`() = runTest {
        viewModel.onLoginTextChanged("wefwefwef")
        viewModel.onPasswordTextChanged("")

        viewModel.onLoginClicked()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == null)
    }

    @Test
    fun `onLoginClicked, login and password valid, login success, navigate`() = runTest {
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

        viewModel.onLoginClicked()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == LoginScreenNavRoute.MainScreen)
    }

    @Test
    fun `onLoginClicked, login and password valid, login error, don't navigate`() = runTest {
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

        viewModel.onLoginClicked()

        advanceUntilIdle()

        assert(viewModel.navigationEvent.value == null)
    }

    @Test
    fun `onLoginClicked, login and password valid, login success, save credits into dataStore`() =
        runTest {
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

            viewModel.onLoginClicked()

            advanceUntilIdle()

            assert(viewModel.navigationEvent.value == LoginScreenNavRoute.MainScreen)
            verify(userDataStore).writeLogin(login)
            verify(userDataStore).writePassword(password)
        }

    @Test
    fun `onForgotPasswordClicked, navigate to EnterLoginScreen`() {
        val login = "wefwefwe"

        viewModel.onLoginTextChanged(login)
        viewModel.onForgotPasswordClicked()

        assert(viewModel.navigationEvent.value == LoginScreenNavRoute.EnterLoginScreen(login))
    }

    @Test
    fun `onPasswordTextChanged, uiState update password`() {
        val password = "wefwefwe"

        viewModel.onPasswordTextChanged(password)

        assert(viewModel.uiState.value.password == password)
    }

    @Test
    fun `onLoginTextChanged, uiState update login`() {
        val login = "wefwefwe"

        viewModel.onLoginTextChanged(login)

        assert(viewModel.uiState.value.login == login)
    }
}