package com.justparokq.feature.feature_toggle.internal.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.common_composables.ColorBackground
import com.justparokq.feature.feature_toggle.R
import com.justparokq.feature.feature_toggle.api.FeatureToggleAction
import com.justparokq.feature.feature_toggle.api.FeatureToggleScreenState
import com.justparokq.feature.feature_toggle.internal.presentation.composable.FeatureToggleItem

@Composable
internal fun InternalFeatureToggleScreen(
    uiState: State<FeatureToggleScreenState>,
    processAction: (FeatureToggleAction) -> Unit,
) {
    ColorBackground(color = MaterialTheme.colors.background) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.feature_toggle_screen_title),
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(uiState.value.list) { ftItem ->
                FeatureToggleItem(
                    ftItem,
                    onClickItem = { isSelected ->
                        processAction(FeatureToggleAction.ToggleClicked(ftItem, isSelected))
                    },
                    onItemLongClick = {
                        processAction(FeatureToggleAction.ItemLongClick(ftItem))
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        AnimatedVisibility(
            visible = uiState.value.snackBarMessage.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            DescriptionPopUp(uiState.value.snackBarMessage)
        }
    }
}

@Composable
internal fun DescriptionPopUp(message: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 10.dp
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.onSurface
            ),
            modifier = Modifier.padding(20.dp)
        )
    }
}