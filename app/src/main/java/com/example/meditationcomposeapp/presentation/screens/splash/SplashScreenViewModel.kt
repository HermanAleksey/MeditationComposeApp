package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.utils.printEventLog
import com.example.meditationcomposeapp.model.entity.NetworkResponse
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
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun onLaunchSplashScreen(
        navigator: DestinationsNavigator
    ) {
        viewModelScope.launch {
            val login = userDataStore.readLogin().first()
            val password = userDataStore.readPassword().first()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                logIn(login, password, navigator)
            } else {
                navigator.navigate(
                    EnterScreenDestination()
                )
            }
        }
    }

    private suspend fun logIn(login: String, password: String, navigator: DestinationsNavigator) {
        loginUseCase(login, password).collect {
            when (it) {
                is NetworkResponse.Success<*> -> {
                    printEventLog("SplashScreen", "Success")
                    navigator.navigate(
                        MainScreenDestination()
                    )
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
}