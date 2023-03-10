package com.example.splash_screen.api

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.NavigationBaseViewModel
import com.example.core.authentication_source.api.use_case.LoginUseCase
import com.example.core.data_store.UserDataStore
import com.example.core.model.NetworkResponse
import com.example.core.model.updates.CompareResult
import com.example.core.model.updates.toVersion
import com.example.core.updates_history.source.db.UpdateDescriptionDBRepository
import com.example.core.updates_history.use_case.GetAppUpdatesHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val loginUseCase: LoginUseCase,
    private val updateDescriptionDBRepository: UpdateDescriptionDBRepository,
    private val getAppUpdatesHistoryUseCase: GetAppUpdatesHistoryUseCase,
) : NavigationBaseViewModel<SplashScreenNavRoute>() {

    private lateinit var currentVersionName: String

    fun onLaunchSplashScreen(versionName: String) {
        viewModelScope.launch {
            currentVersionName = versionName
            checkLastUpdateVersion()

            val login = userDataStore.readLogin().first()
            val password = userDataStore.readPassword().first()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                logIn(login, password)
            } else {
                navigationEventTransaction {
                    _navigationEvent.emit(
                        SplashScreenNavRoute.EnterScreen
                    )
                }
            }
        }
    }

    private suspend fun logIn(
        login: String,
        password: String,
    ) {
        loginUseCase(login, password).collect {
            when (it) {
                is NetworkResponse.Success<*> -> {
                    navigationEventTransaction {
                        _navigationEvent.emit(
                            SplashScreenNavRoute.MainScreen
                        )
                    }
                }
                is NetworkResponse.Failure<*> -> {
                    navigationEventTransaction {
                        _navigationEvent.emit(
                            SplashScreenNavRoute.EnterScreen
                        )
                    }
                }
                is NetworkResponse.Loading<*> -> {
                    //do nothing
                }
            }
        }
    }

    private suspend fun checkLastUpdateVersion() {
        val lastInstalledVersion = userDataStore.readLastUpdateVersion().first()
        if (lastInstalledVersion.toVersion()
                .compare(currentVersionName.toVersion()) == CompareResult.EQUALS
        ) return

        getAppUpdatesHistoryUseCase(lastInstalledVersion)
            .collect {
                if (it is NetworkResponse.Success) {
                    it.data?.forEach { update ->
                        updateDescriptionDBRepository.insertAll(update)
                    }
                }
            }

        userDataStore.writeLastUpdateVersion(currentVersionName)
    }
}