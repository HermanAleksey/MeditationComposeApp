package com.example.feature.profile.api

import androidx.lifecycle.viewModelScope
import com.example.common.view_model.BaseViewModel
import com.example.common.view_model.NavigationBaseViewModel
import com.example.core.data_store.use_case.ClearAuthDataUseCase
import com.example.feature.profile.api.ProfileScreenNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val clearAuthDataUseCase: ClearAuthDataUseCase,
) : NavigationBaseViewModel<ProfileScreenNavRoute>() {

    fun onLogOutClicked() {
        viewModelScope.launch {
            clearAuthDataUseCase()
            _navigationEvent.update {
                ProfileScreenNavRoute.EnterScreen()
            }
        }
    }
}