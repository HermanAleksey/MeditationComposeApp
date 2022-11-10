package com.example.meditationcomposeapp.presentation.screens.splash

import com.example.meditationcomposeapp.FakeObjects
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SplashScreenViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Mock
    private lateinit var userDataStore: UserDataStore

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var updateDescriptionRepository: UpdateDescriptionRepository

    private lateinit var viewModel: SplashScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        MockitoAnnotations.openMocks(this)
        viewModel = SplashScreenViewModel(
            userDataStore, loginUseCase, updateDescriptionRepository
        )

        whenever(userDataStore.readLastUpdateVersion()).thenReturn(flow { emit("1.0.0") })
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onLaunchSplashScreen, check login and password success, logIn success, navigate to main`() =
        scope.runTest {
            val login = "q"
            val password = "2"
            var mainScreenWasLaunched = false
            var loginScreenWasLaunched = false

            whenever(loginUseCase(anyString(), anyString()))
                .thenReturn(flow {
                    emit(
                        NetworkResponse.Success(
                            FakeObjects.getFakeProfile()
                        )
                    )
                })
            whenever(userDataStore.readLogin()).thenReturn(flow { emit(login) })
            whenever(userDataStore.readPassword()).thenReturn(flow { emit(password) })

            viewModel.onLaunchSplashScreen({
                mainScreenWasLaunched = true
            }, {
                loginScreenWasLaunched = true
            })

            advanceUntilIdle()

            assert(mainScreenWasLaunched)
            assert(!loginScreenWasLaunched)
        }

    @Test
    fun `onLaunchSplashScreen, check login and password success, logIn fail, navigate to login`() =
        scope.runTest {
            val login = "q"
            val password = "2"
            var mainScreenWasLaunched = false
            var loginScreenWasLaunched = false

            whenever(loginUseCase(anyString(), anyString()))
                .thenReturn(flow {
                    emit(
                        NetworkResponse.Failure(data = null, error = "")
                    )
                })
            whenever(userDataStore.readLogin()).thenReturn(flow { emit(login) })
            whenever(userDataStore.readPassword()).thenReturn(flow { emit(password) })

            viewModel.onLaunchSplashScreen({
                mainScreenWasLaunched = true
            }, {
                loginScreenWasLaunched = true
            })

            advanceUntilIdle()

            assert(!mainScreenWasLaunched)
            assert(loginScreenWasLaunched)
        }

    @Test
    fun `onLaunchSplashScreen, check login and password empty, navigate to login`() =
        scope.runTest {
            val login = ""
            val password = ""
            var mainScreenWasLaunched = false
            var loginScreenWasLaunched = false

            whenever(userDataStore.readLogin()).thenReturn(flow { emit(login) })
            whenever(userDataStore.readPassword()).thenReturn(flow { emit(password) })

            viewModel.onLaunchSplashScreen({
                mainScreenWasLaunched = true
            }, {
                loginScreenWasLaunched = true
            })

            advanceUntilIdle()

            assert(!mainScreenWasLaunched)
            assert(loginScreenWasLaunched)
        }
}