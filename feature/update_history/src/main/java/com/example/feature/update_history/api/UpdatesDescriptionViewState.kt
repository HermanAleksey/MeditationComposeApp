package com.example.feature.update_history.api

import androidx.compose.runtime.Stable
import com.justparokq.core.model.updates.UpdateDescriptionModel

@Stable
data class UpdatesDescriptionViewState(
    val updatesList: List<UpdateDescriptionModel> = emptyList(),
    val isRateUsCardVisible: Boolean = true
)