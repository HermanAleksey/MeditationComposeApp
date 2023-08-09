package com.justparokq.mediose.presentation

import androidx.lifecycle.ViewModel
import com.example.design_system.dialog.MedioseDialogProvider
import com.example.design_system.toolbar.ToolbarProvider
import com.justparokq.mediose.presentation.navigation.DestinationWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val _bottomBarIsVisible = MutableStateFlow(false)
    val bottomBarIsVisible = _bottomBarIsVisible.asStateFlow()

    private val _toolbarIsVisible = MutableStateFlow(false)
    val toolbarIsVisible = _toolbarIsVisible.asStateFlow()

    private val _dialogIsVisible = MutableStateFlow(false)
    val dialogIsVisible = _dialogIsVisible.asStateFlow()

    private val _dialogProvider: MutableStateFlow<MedioseDialogProvider?> = MutableStateFlow(null)
    val dialogProvider = _dialogProvider.asStateFlow()

    private val _toolbarProvider: MutableStateFlow<ToolbarProvider?> = MutableStateFlow(null)
    val toolbarProvider = _toolbarProvider.asStateFlow()

    fun onDestinationChanged(destination: DestinationWrapper) {
        _toolbarIsVisible.update {
            destination.toolbarVisible
        }
        _bottomBarIsVisible.update {
            destination.bottomBarVisible
        }
    }

    fun onShowDialogRequested(dialogProvider: MedioseDialogProvider) {
        _dialogProvider.update {
            dialogProvider.apply {
                addOnDismissListener {
                    _dialogIsVisible.update {
                        false
                    }
                }
            }
        }
        _dialogIsVisible.update {
            true
        }
    }

    fun onCloseDialog() {
        _dialogIsVisible.update {
            false
        }
        _dialogProvider.update {
            null
        }
    }

    fun onLaunch(initialToolbarProvider: ToolbarProvider) {
        _toolbarProvider.update {
            initialToolbarProvider
        }
    }
}