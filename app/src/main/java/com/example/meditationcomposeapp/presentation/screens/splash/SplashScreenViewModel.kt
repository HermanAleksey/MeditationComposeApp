package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.login_flow.CompareResult
import com.example.meditationcomposeapp.model.entity.login_flow.toVersion
import com.example.meditationcomposeapp.model.usecase.authentication.GetAppUpdatesHistoryUseCase
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val loginUseCase: LoginUseCase,
    private val updateDescriptionRepository: UpdateDescriptionRepository,
    private val getAppUpdatesHistoryUseCase: GetAppUpdatesHistoryUseCase,
) : ViewModel() {

    fun onLaunchSplashScreen(
        navigator: DestinationsNavigator,
    ) {
        viewModelScope.launch {
            checkLastUpdateVersion()

            val login = userDataStore.readLogin().first()
            val password = userDataStore.readPassword().first()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                logIn(login, password, navigator)
            } else {
                with(navigator) {
                    navigate(
                        EnterScreenDestination()
                    )
                }
            }
        }
    }

    private suspend fun logIn(login: String, password: String, navigator: DestinationsNavigator) {
        loginUseCase(login, password).collect {
            when (it) {
                is NetworkResponse.Success<*> -> {
                    with(navigator) {
                        navigate(
                            MainScreenDestination()
                        )
                    }
                }
                is NetworkResponse.Failure<*> -> {
                    //on error show pop-up
                }
                is NetworkResponse.Loading<*> -> {
                    //todo splash screen loading
//                    setLoading(it.isLoading)
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