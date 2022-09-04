package com.example.meditationcomposeapp.presentation.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationcomposeapp.data_source.data_store.UserDataStore
import com.example.meditationcomposeapp.data_source.utils.TAG
import com.example.meditationcomposeapp.model.entity.NetworkResponse
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.presentation.navigation.navigateFunc
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
        navigateToEnterScreen: navigateFunc,
        navigateToHomeScreen: navigateFunc
    ) {
        viewModelScope.launch {
            val login = userDataStore.readLogin().first()
            val password = userDataStore.readPassword().first()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                logIn(login, password, navigateToHomeScreen)
            } else {
                navigateToEnterScreen()
            }
        }
    }

    private suspend fun logIn(login: String, password: String, navigateToHomeScreen: navigateFunc) {
        loginUseCase(login, password).collect {
            when (it) {
                is NetworkResponse.Success<*> -> {
                    Log.d(TAG.TAG_D, "${javaClass.canonicalName}: Success")
                    navigateToHomeScreen()
                }
                is NetworkResponse.Failure<*> -> {
                    //on error show pop-up
                    Log.d(TAG.TAG_D, "${javaClass.canonicalName}: Error")
                }
                is NetworkResponse.Loading<*> -> {
                    //todo splash screen loading
//                    setLoading(it.isLoading)
                    Log.d(TAG.TAG_D, "${javaClass.canonicalName}: Loading:${it.isLoading}")
                }
            }
        }
    }
}