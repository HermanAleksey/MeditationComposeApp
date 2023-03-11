package com.example.design_system.dialog

import androidx.compose.runtime.Composable

abstract class MedioseDialogProvider {

    protected var onDismiss: () -> Unit = {}

    fun addOnDismissListener(action: () -> Unit) {
        onDismiss = action
    }

    @Composable
    abstract fun Display()
}