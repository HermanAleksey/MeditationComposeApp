package com.example.feature.update_history.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.utils.DateFormat
import com.example.common.utils.formatMillisIntoDate
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.design_system.AppTheme
import com.example.design_system.dialog.MeditationDialogFormer
import com.example.feature.update_history.R

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

@Composable
internal fun UpdateDescriptionElement(model: UpdateDescriptionModel) {
    val locale = LocalContext.current.resources.configuration.locales[0]
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(id = R.string.app_update_version, model.versionName),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            Text(
                text = stringResource(
                    id = R.string.app_update_time,
                    formatMillisIntoDate(
                        milliSeconds = model.updateReleaseTime,
                        dateFormat = DateFormat.DD_MM_YYYY,
                        locale = locale
                    )
                ),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = model.updateTitle,
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = model.updateDescription,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
    }
}

@Preview
@Composable
internal fun UpdateDescriptionElementPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        )
        UpdateDescriptionElement(
            UpdateDescriptionModel("10.10.10", 1667045395445, "title", "desc", true),
        )
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
                        UpdateDescriptionModel("10.10.10", 1667045395445, "title", "desc", true),
                        UpdateDescriptionModel("10.10.10", 1667045395445, "title", "desc", true)
                    )
                )
            }
        )
    }
}