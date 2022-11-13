package com.example.meditationcomposeapp.presentation.screens.splash

import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.FakeObjects
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.UpdateDescriptionModel
import com.example.meditationcomposeapp.model.usecase.authentication.GetAppUpdatesHistoryUseCase
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
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
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

    @Mock
    private lateinit var getAppUpdatesHistoryUseCase: GetAppUpdatesHistoryUseCase

    private lateinit var viewModel: SplashScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        MockitoAnnotations.openMocks(this)
        viewModel = SplashScreenViewModel(
            userDataStore,
            loginUseCase,
            updateDescriptionRepository,
            getAppUpdatesHistoryUseCase,
        )
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

            whenever(userDataStore.readLastUpdateVersion()).thenReturn(flow { emit("0.0.1") })
            whenever(getAppUpdatesHistoryUseCase(anyString())).thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        error = null
                    )
                )
            })
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

            whenever(userDataStore.readLastUpdateVersion()).thenReturn(flow { emit("0.0.1") })
            whenever(getAppUpdatesHistoryUseCase(anyString())).thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        error = null
                    )
                )
            })
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

            whenever(userDataStore.readLastUpdateVersion()).thenReturn(flow { emit("0.0.1") })
            whenever(getAppUpdatesHistoryUseCase(anyString())).thenReturn(flow {
                emit(
                    NetworkResponse.Failure(
                        error = null
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

            assert(!mainScreenWasLaunched)
            assert(loginScreenWasLaunched)
        }

    @Test
    fun `checkLastUpdateVersion, is newest version, don't request update info`() {
        viewModel.onLaunchSplashScreen({ }, {})

        verify(getAppUpdatesHistoryUseCase, never()).invoke(anyString())
    }

    @Test
    fun `checkLastUpdateVersion, not newest version, no new versions info, insert new updates info into db`() =
        scope.runTest {
            val installedVersion = "0.0.1"
            whenever(userDataStore.readLastUpdateVersion()).thenReturn(
                flow {
                    emit(installedVersion)
                }
            )
            whenever(getAppUpdatesHistoryUseCase.invoke(anyString())).thenReturn(
                flow {
                    emit(
                        NetworkResponse.Success(
                            listOf()
                        )
                    )
                }
            )
            whenever(userDataStore.readLogin()).thenReturn(flow { emit("login") })
            whenever(userDataStore.readPassword()).thenReturn(flow { emit("password") })
            whenever(loginUseCase(anyString(), anyString()))
                .thenReturn(flow {
                    emit(
                        NetworkResponse.Failure(data = null, error = "")
                    )
                })

            viewModel.onLaunchSplashScreen({}, {})

            advanceUntilIdle()

            verify(userDataStore).writeLastUpdateVersion(BuildConfig.VERSION_NAME)
        }

    @Test
    fun `checkLastUpdateVersion, not newest version, add new versions info into db`() =
        scope.runTest {
            val installedVersion = "0.0.1"
            val updates = listOf(
                FakeObjects.getFakeUpdateDescriptionModel()
            )

            whenever(userDataStore.readLastUpdateVersion()).thenReturn(
                flow {
                    emit(installedVersion)
                }
            )
            whenever(getAppUpdatesHistoryUseCase.invoke(anyString())).thenReturn(
                flow {
                    emit(
                        NetworkResponse.Success(
                            updates
                        )
                    )
                }
            )
            whenever(userDataStore.readLogin()).thenReturn(flow { emit("login") })
            whenever(userDataStore.readPassword()).thenReturn(flow { emit("password") })
            whenever(loginUseCase(anyString(), anyString()))
                .thenReturn(flow {
                    emit(
                        NetworkResponse.Failure(data = null, error = "")
                    )
                })

            viewModel.onLaunchSplashScreen({}, {})

            advanceUntilIdle()

            verify(updateDescriptionRepository).insertAll(*updates.toTypedArray())
        }
}