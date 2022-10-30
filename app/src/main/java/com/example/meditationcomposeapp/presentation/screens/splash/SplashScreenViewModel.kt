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

        val lastInstalledVersion = userDataStore.readLastUpdateVersion().first()
        if (lastInstalledVersion.compareToVersion(currentVersionName) == COMPARATION_RESULT.EQUALS) return

        val version0_0_1 = UpdateDescriptionModel(
            versionName = "0.0.1",
            updateReleaseTime = 1667034395445,
            updateTitle = "Project initialization!",
            updateDescription = "Project was created. This update created in order to show origin.",
            wasShown = false
        )
        val version0_5_0 = UpdateDescriptionModel(
            versionName = "0.5.0",
            updateReleaseTime = 1667034395445,
            updateTitle = "A lot of Beer!",
            updateDescription = "Added new beer api with more details. Also detailed screen were added for" +
                    "each beer! Just click on it. Button on items of the list were meant to navigate to" +
                    "beer page on an online store or similar.",
            wasShown = false
        )
        val version0_6_0 = UpdateDescriptionModel(
            versionName = "0.6.0",
            updateReleaseTime = 1667138968792,
            updateTitle = "Update notes!",
            updateDescription = "Now you can see what is new in the app with less effort! Also you can check updates history.",
            wasShown = false
        )
        val versions = listOf(
            version0_0_1,
            version0_5_0,
            version0_6_0
        )

        versions.forEach { updateDesc ->
            //if this version update wasn't added into db yet - add it
            if (updateDesc.versionName.compareToVersion(lastInstalledVersion)
                == COMPARATION_RESULT.BIGGER
            )
                updateDescriptionRepository.insertAll(updateDesc)
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