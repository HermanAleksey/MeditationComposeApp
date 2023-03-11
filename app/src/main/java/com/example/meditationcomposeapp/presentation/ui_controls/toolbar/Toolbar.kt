package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.compose.runtime.Composable
import com.example.design_system.dialog.DialogController
import com.example.design_system.toolbar.ToolbarProvider
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.state.MedioseToolbar

class MedioseToolbarProvider(
    private val viewModel: ToolbarViewModel,
    private val dialogController: DialogController
): ToolbarProvider() {

    @Composable
    override fun Display() {
        MedioseToolbar(
            viewModel = viewModel,
            dialogController = dialogController
        )
    }

}