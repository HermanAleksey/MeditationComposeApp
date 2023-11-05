package com.justparokq.feature.mediose

import com.justparokq.core.coroutines_test.CoroutinesTestRule
import com.justparokq.core.authentication_source.api.use_case.LoginUseCase
import com.justparokq.core.data_store.user.UserDataStore
import com.justparokq.core.model.NetworkResponse
import com.justparokq.core.updates_history.use_case.GetAppUpdatesHistoryUseCase
import com.justparokq.core.updates_history.use_case.InsertAllUpdatesDescriptionsUseCase
import com.justparokq.feature.splash_screen.api.SplashScreenNavRoute
import com.justparokq.feature.splash_screen.api.SplashScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
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
    private lateinit var insertAllUpdatesDescriptionsUseCase: InsertAllUpdatesDescriptionsUseCase

    @Mock
    private lateinit var getAppUpdatesHistoryUseCase: GetAppUpdatesHistoryUseCase

    private lateinit var viewModel: SplashScreenViewModel

    @Before
    fun setup() {
        viewModel = SplashScreenViewModel(
            userDataStore,
            loginUseCase,
            insertAllUpdatesDescriptionsUseCase,
            getAppUpdatesHistoryUseCase,
        )
    }

    @Test
    fun `onLaunchSplashScreen, check login and password success, logIn success, navigate to main`() =
        runTest {
            val login = "q"
            val password = "2"
            val versionName = "1.0.1"

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

            viewModel.onLaunchSplashScreen(versionName)

            val sharedFlowResult = mutableListOf<SplashScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            Assert.assertEquals(
                sharedFlowResult.firstOrNull(),
                SplashScreenNavRoute.MainScreen
            )
            job.cancel()
        }

    @Test
    fun `onLaunchSplashScreen, check login and password success, logIn fail, navigate to enter screen`() =
        runTest {
            val login = "q"
            val password = "2"
            val versionName = "1.0.1"

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

            viewModel.onLaunchSplashScreen(versionName)

            val sharedFlowResult = mutableListOf<SplashScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            Assert.assertEquals(
                sharedFlowResult.firstOrNull(),
                SplashScreenNavRoute.EnterScreen
            )
            job.cancel()
        }

    @Test
    fun `onLaunchSplashScreen, check login and password empty, navigate to enter screen`() =
        runTest {
            val login = ""
            val password = ""
            val versionName = "1.0.1"

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

            viewModel.onLaunchSplashScreen(versionName)

            val sharedFlowResult = mutableListOf<SplashScreenNavRoute?>()
            val job = launch {
                viewModel.navigationEvent.toList(sharedFlowResult)
            }
            advanceUntilIdle()

            Assert.assertEquals(
                sharedFlowResult.firstOrNull(),
                SplashScreenNavRoute.EnterScreen
            )
            job.cancel()
        }

    @Test
    fun `checkLastUpdateVersion, is newest version, don't request update info`() {
        val versionName = "1.0.1"

        viewModel.onLaunchSplashScreen(versionName)

        verify(getAppUpdatesHistoryUseCase, never()).invoke(anyString())
    }

    @Test
    fun `checkLastUpdateVersion, not newest version, no new versions info, insert new updates info into db`() =
        runTest {
            val installedVersion = "0.0.1"
            val versionName = "1.0.1"
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

            viewModel.onLaunchSplashScreen(versionName)

            advanceUntilIdle()

            verify(userDataStore).writeLastUpdateVersion(versionName)
        }

    @Test
    fun `checkLastUpdateVersion, not newest version, add new versions info into db`() =
        runTest {
            val installedVersion = "0.0.1"
            val versionName = "1.0.1"
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

            viewModel.onLaunchSplashScreen(versionName)

            advanceUntilIdle()

            verify(insertAllUpdatesDescriptionsUseCase).invoke(updates)
        }
}