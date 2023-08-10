package com.justparokq.feature.update_history.api

import androidx.compose.runtime.Composable
import com.justparokq.feature.update_history.internal.UpdateDescriptionDialog
import com.justparokq.core.design_system.dialog.MedioseDialogProvider
import com.justparokq.core.design_system.dialog.MeditationDialogFormer
import com.justparokq.core.model.updates.UpdateDescriptionModel

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