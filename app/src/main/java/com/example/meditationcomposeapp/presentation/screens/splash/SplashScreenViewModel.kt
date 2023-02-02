package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.CompareResult
import com.example.meditationcomposeapp.model.entity.login_flow.toVersion
import com.example.meditationcomposeapp.model.usecase.authentication.GetAppUpdatesHistoryUseCase
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.presentation.navigation.Event
import com.example.meditationcomposeapp.presentation.screens.BaseViewModel
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val loginUseCase: LoginUseCase,
    private val updateDescriptionRepository: UpdateDescriptionRepository,
    private val getAppUpdatesHistoryUseCase: GetAppUpdatesHistoryUseCase,
) : BaseViewModel() {

    fun onLaunchSplashScreen() {
        viewModelScope.launch {
            checkLastUpdateVersion()

            val login = userDataStore.readLogin().first()
            val password = userDataStore.readPassword().first()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                logIn(login, password)
            } else {
                _navigationEvent.update {
                   Event(NavigationEvent.Navigate(EnterScreenDestination))
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
                    _navigationEvent.update {
                        Event(NavigationEvent.Navigate(MainScreenDestination))
                    }
                }
                is NetworkResponse.Failure<*> -> {
                    _navigationEvent.update {
                        Event(NavigationEvent.Navigate(EnterScreenDestination))
                    }
                }
                is NetworkResponse.Loading<*> -> {
                    //do nothing
                }
            }
        }
    }

    private suspend fun checkLastUpdateVersion() {
        val currentVersionName = BuildConfig.VERSION_NAME

        val lastInstalledVersion = userDataStore.readLastUpdateVersion().first()
        if (lastInstalledVersion.toVersion()
                .compare(currentVersionName.toVersion()) == CompareResult.EQUALS
        ) return
        
        getAppUpdatesHistoryUseCase(lastInstalledVersion)
            .collect {
                if (it is NetworkResponse.Success) {
                    it.data?.forEach { update ->
                        updateDescriptionRepository.insertAll(update)
                    }
                }
            }

        userDataStore.writeLastUpdateVersion(currentVersionName)
    }
}