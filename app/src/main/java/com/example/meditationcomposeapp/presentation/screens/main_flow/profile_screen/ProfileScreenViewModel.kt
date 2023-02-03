package com.example.meditationcomposeapp.presentation.screens.main_flow.profile_screen

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.BaseViewModel
import com.example.core.data_store.use_case.ClearAuthDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val clearAuthDataUseCase: ClearAuthDataUseCase,
) : BaseViewModel() {

    fun onLogOutClicked() {
        viewModelScope.launch {
            clearAuthDataUseCase()
                //todo restore
//            _navigationEvent.update {
//                Event(
//                    NavigationEvent.NavigateWithPop(
//                        direction = EnterScreenDestination(),
//                        popUpTo = SplashScreenDestination.route,
//                        inclusive = false
//                    )
//                )
//            }
        }
    }
}