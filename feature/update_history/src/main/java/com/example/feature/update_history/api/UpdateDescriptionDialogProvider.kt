package com.example.feature.update_history.api

import androidx.compose.runtime.Composable
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.design_system.dialog.MedioseDialogProvider
import com.example.design_system.dialog.MeditationDialogFormer
import com.example.feature.update_history.internal.UpdateDescriptionDialog

class UpdateDescriptionDialogProvider(
    private val updatesLog: List<UpdateDescriptionModel>,
    private val onDismissSideEffect: () -> Unit = {}
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
                UpdateDescriptionDialog(
                    updatesLog
                )
            }
        )
    }
}