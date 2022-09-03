package com.example.meditationcomposeapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.example.meditationcomposeapp.model.usecase.authentication.LoginUseCase
import com.example.meditationcomposeapp.presentation.navigation.navigateFunc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
//    private val userPreferences: ,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun onLaunchSplashScreen(
        navigateToEnterScreen: navigateFunc,
        navigateToHomeScreen: navigateFunc
    ) {
        //check if user log/pass stored on device and than send login request.
        val userIsLoggerIn = false
        if (userIsLoggerIn){
            //user is logged in
            navigateToHomeScreen()
        } else {
            navigateToEnterScreen()
        }
    }
}