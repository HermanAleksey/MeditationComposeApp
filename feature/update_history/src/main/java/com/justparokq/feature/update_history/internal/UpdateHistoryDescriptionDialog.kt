package com.justparokq.feature.update_history.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.core.design_system.dialog.MeditationDialogFormer
import com.justparokq.core.model.updates.UpdateDescriptionModel
import com.justparokq.core.model.updates.Version
import com.justparokq.feature.update_history.R
import com.justparokq.feature.update_history.internal.composables.UpdateDescriptionElement

@Composable
internal fun UpdateDescriptionDialog(
    updatesLog: List<UpdateDescriptionModel>,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.update_notes_dialog_title),
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.onSurface
                ),
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
            )
        }
        itemsIndexed(updatesLog) { index, item ->
            UpdateDescriptionElement(item)

            if (index < updatesLog.lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .height(1.dp)
                            .fillMaxWidth(0.7f)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
internal fun UpdateDescriptionDialogPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        )
        MeditationDialogFormer(
            onDismissRequest = { },
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            dialogContent = {
                UpdateDescriptionDialog(
                    listOf(
                        UpdateDescriptionModel(Version(1,0,1), 1667045395445, "title", "desc", true),
                        UpdateDescriptionModel(Version(1,0,5), 1667045395445, "title", "desc", true)
                    )
                )
            }
        )
    }
}