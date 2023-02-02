package com.example.meditationcomposeapp.presentation.screens.splash

import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.CoroutinesTestRule
import com.example.meditationcomposeapp.FakeObjects
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.usecase.authentication.GetAppUpdatesHistoryUseCase
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SplashScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var rule = CoroutinesTestRule()

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
        viewModel = SplashScreenViewModel(
            userDataStore,
            loginUseCase,
            updateDescriptionRepository,
            getAppUpdatesHistoryUseCase,
        )
    }

    @Test
    fun `onLaunchSplashScreen, check login and password success, logIn success, navigate to main`() =
        runTest {
            val login = "q"
            val password = "2"

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

            viewModel.onLaunchSplashScreen()

            advanceUntilIdle()

            Assert.assertEquals(
                NavigationEvent.Navigate(
                    MainScreenDestination()
                ).toString(),
                viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
            )
        }

    @Test
    fun `onLaunchSplashScreen, check login and password success, logIn fail, navigate to enter screen`() =
        runTest {
            val login = "q"
            val password = "2"

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

            viewModel.onLaunchSplashScreen()

            advanceUntilIdle()

            Assert.assertEquals(
                NavigationEvent.Navigate(
                    EnterScreenDestination()
                ).toString(),
                viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
            )
        }

    @Test
    fun `onLaunchSplashScreen, check login and password empty, navigate to enter screen`() =
        runTest {
            val login = ""
            val password = ""

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

            viewModel.onLaunchSplashScreen()

            advanceUntilIdle()

            Assert.assertEquals(
                NavigationEvent.Navigate(
                    EnterScreenDestination()
                ).toString(),
                viewModel.navigationEvent.value.getNavigationIfNotHandled().toString()
            )
        }

    @Test
    fun `checkLastUpdateVersion, is newest version, don't request update info`() {
        viewModel.onLaunchSplashScreen()

        verify(getAppUpdatesHistoryUseCase, never()).invoke(anyString())
    }

    @Test
    fun `checkLastUpdateVersion, not newest version, no new versions info, insert new updates info into db`() =
        runTest {
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

            viewModel.onLaunchSplashScreen()

            advanceUntilIdle()

            verify(userDataStore).writeLastUpdateVersion(BuildConfig.VERSION_NAME)
        }

    @Test
    fun `checkLastUpdateVersion, not newest version, add new versions info into db`() =
        runTest {
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

            viewModel.onLaunchSplashScreen()

            advanceUntilIdle()

            verify(updateDescriptionRepository).insertAll(*updates.toTypedArray())
        }
}