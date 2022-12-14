package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import androidx.compose.runtime.Composable
import com.example.meditationcomposeapp.presentation.ui_controls.dialog.DialogController
import com.example.meditationcomposeapp.presentation.ui_controls.toolbar.state.ToolbarMain

sealed class ToolbarState {

    class ToolbarMainState(val dialogController: DialogController) : ToolbarState()
}

@Composable
fun Toolbar(
    toolbarState: ToolbarState,
    viewModel: ToolbarViewModel,
) {
    when (toolbarState) {
        is ToolbarState.ToolbarMainState -> ToolbarMain(
            viewModel = viewModel,
            dialogController = toolbarState.dialogController
        )
    }
}