package com.example.feature.update_history.api

import androidx.compose.runtime.Stable
import com.example.core.model.updates.UpdateDescriptionModel

@Stable
data class UpdatesDescriptionViewState(
    val updatesList: List<UpdateDescriptionModel> = emptyList(),
    val isRateUsCardVisible: Boolean = true
)