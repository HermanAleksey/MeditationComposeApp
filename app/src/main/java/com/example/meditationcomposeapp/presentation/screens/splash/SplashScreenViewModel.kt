package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.data_store.UserDataStoreImpl
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.presentation.screens.destinations.EnterScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.utils.getVersionDescriptions
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
        if (lastInstalledVersion.compareToVersion(currentVersionName) == COMPARATION_RESULT.EQUALS) return

        val versions = getVersionDescriptions()

        versions.forEach { updateDesc ->
            //if this version update wasn't added into db yet - add it
            if (updateDesc.versionName.compareToVersion(lastInstalledVersion)
                == COMPARATION_RESULT.BIGGER
            )
                updateDescriptionRepository.insertAll(updateDesc)
        }

        userDataStore.writeLastUpdateVersion(currentVersionName)
    }

    fun test_1() = 1
}

private fun String.compareToVersion(versionName: String): COMPARATION_RESULT {
    val version1 = this.split(".")
    val version2 = versionName.split(".")

    if (version1[0] > version2[0]) return COMPARATION_RESULT.BIGGER
    if (version1[0] < version2[0]) return COMPARATION_RESULT.SMALLER

    if (version1[1] > version2[1]) return COMPARATION_RESULT.BIGGER
    if (version1[1] < version2[1]) return COMPARATION_RESULT.SMALLER

    if (version1[2] > version2[2]) return COMPARATION_RESULT.BIGGER
    if (version1[2] < version2[2]) return COMPARATION_RESULT.SMALLER

    return COMPARATION_RESULT.EQUALS
}

enum class COMPARATION_RESULT {
    BIGGER, SMALLER, EQUALS
}