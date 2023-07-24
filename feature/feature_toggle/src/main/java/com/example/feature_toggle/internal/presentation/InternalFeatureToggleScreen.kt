package com.example.feature_toggle.internal.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.design_system.common_composables.ColorBackground
import com.example.feature_toggle.api.FeatureToggleAction
import com.example.feature_toggle.api.FeatureToggleScreenState

@Composable
internal fun InternalFeatureToggleScreen(
    uiState: State<FeatureToggleScreenState>,
    processAction: (FeatureToggleAction) -> Unit,
) {
    ColorBackground(color = MaterialTheme.colors.background) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                Text(text = "Developer menu")
            }

            items(uiState.value.list) { ftItem ->
                FeatureToggleItem(
                    ftItem,
                    onClickItem = {
                        processAction(FeatureToggleAction.ToggleClicked(ftItem))
                    },
                    onItemPressAndHold = {
                        processAction(FeatureToggleAction.ItemLongHold(ftItem))
                    }
                )
            }
        }
    }
}