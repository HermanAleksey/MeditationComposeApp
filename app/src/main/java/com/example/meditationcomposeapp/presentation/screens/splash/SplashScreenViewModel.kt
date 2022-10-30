package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.BuildConfig
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.repository.update_description.UpdateDescriptionRepository
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel
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
    val userDataStore: UserDataStore,
    val loginUseCase: LoginUseCase,
    val updateDescriptionRepository: UpdateDescriptionRepository,
) : ViewModel() {

    fun onLaunchSplashScreen(
        navigator: DestinationsNavigator,
    ) {
        viewModelScope.launch {
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
                    printEventLog("SplashScreen", "Success")
                    with(navigator) {
                        navigate(
                            MainScreenDestination()
                        )
                    }
                }
                is NetworkResponse.Failure<*> -> {
                    //on error show pop-up
                    printEventLog("SplashScreen", "Error")
                }
                is NetworkResponse.Loading<*> -> {
                    //todo splash screen loading
//                    setLoading(it.isLoading)
                    printEventLog("SplashScreen", "Loading:${it.isLoading}")
                }
            }
        }
    }

    private suspend fun checkLastUpdateVersion() {
        val currentVersionName = BuildConfig.VERSION_NAME

        userDataStore.readLastUpdateVersion().collect { lastInstalledVersion ->
            if (lastInstalledVersion.compareToVersion(currentVersionName) == COMPARATION_RESULT.EQUALS) return@collect

            val versions = listOf<UpdateDescriptionModel>(

            )
            val version0_0_1 = UpdateDescriptionModel(
                versionName = "0.0.1",
                updateReleaseTime = 1667034395445,
                updateTitle = "Project initialization!",
                updateDescription = "Project was created. This update created in order to show origin.",
                wasShown = false
            )
            val version0_4_0 = UpdateDescriptionModel(
                versionName = "0.5.0",
                updateReleaseTime = 1667034395445,
                updateTitle = "A lot of Beer!",
                updateDescription = "Added new beer api with more details. Also detailed screen were added for" +
                        "each beer! Just click on it. Button on items of the list were meant to navigate to" +
                        "beer page on an online store or similar.",
                wasShown = false
            )

            versions.forEach {
                //if this version update wasn't added into db yet - add it
                if (it.versionName.compareToVersion(lastInstalledVersion) == COMPARATION_RESULT.BIGGER)
                    updateDescriptionRepository.insertAll(it)
            }
        }

        userDataStore.writeLastUpdateVersion(currentVersionName)
    }

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