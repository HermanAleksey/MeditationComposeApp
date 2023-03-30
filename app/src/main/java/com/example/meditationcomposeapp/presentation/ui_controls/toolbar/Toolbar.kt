package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.design_system.dialog.DialogController
import com.example.design_system.toolbar.ToolbarProvider
import com.example.feature.update_history.api.UpdateDescriptionDialogProvider

class MedioseToolbarProvider(
    private val viewModel: ToolbarViewModel,
    private val dialogController: DialogController,
) : ToolbarProvider() {

    @Composable
    override fun Display() {

        val uiState = viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.onLaunch()
        }

        LaunchedEffect(key1 = uiState.value.lastUpdate) {
            uiState.value.lastUpdate?.let { lastUpdate ->
                if (!lastUpdate.wasShown) {
                    dialogController.show(
                        UpdateDescriptionDialogProvider(
                            listOf(lastUpdate)
                        )
                    )
                }
            }
        }

        LaunchedEffect(
            key1 = uiState.value.updateNotesList,
            key2 = uiState.value.isDialogShown
        ) {
            if (uiState.value.updateNotesList.isNotEmpty() && uiState.value.isDialogShown)
                dialogController.show(
                    UpdateDescriptionDialogProvider(
                        uiState.value.updateNotesList,
                        onDismissSideEffect = {
                            viewModel.onDialogClosed()
                        }
                    )
                )
        }

        MedioseToolbar(
            viewModel = viewModel,
        )
    }
}