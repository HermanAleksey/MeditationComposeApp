package com.justparokq.mediose.presentation.ui_controls.toolbar

import androidx.compose.runtime.Stable
import com.example.core.model.updates.UpdateDescriptionModel

@Stable
data class ToolbarViewState(
    val lastUpdate: UpdateDescriptionModel? = null,
    val isDialogShown: Boolean = false,
)
