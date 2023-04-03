package com.example.meditationcomposeapp.presentation.ui_controls.toolbar.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.AppTheme
import com.example.design_system.dialog.MedioseDialogProvider
import com.example.design_system.dialog.MeditationDialogFormer

class ExitAppDialogProvider(
    private val onDismissSideEffect: () -> Unit = {},
    private val onSubmit: () -> Unit = {},
) : MedioseDialogProvider() {

    @Composable
    override fun Display() {
        MeditationDialogFormer(
            onDismissRequest = {
                onDismiss()
                onDismissSideEffect()
            },
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            dialogContent = {
                ExitAppDialog(
                    onSubmitClick = {
                        onDismiss()
                        onDismissSideEffect()
                        onSubmit()
                    },
                    onDismissRequest = {
                        onDismiss()
                        onDismissSideEffect()
                    },
                )
            }
        )
    }
}

@Preview
@Composable
fun ExitAppDialogProviderPreview() {
    AppTheme {
        ExitAppDialogProvider({}, {}).Display()
    }
}