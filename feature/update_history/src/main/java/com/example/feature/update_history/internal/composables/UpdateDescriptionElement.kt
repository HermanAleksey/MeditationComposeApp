package com.example.feature.update_history.internal.composables

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.utils.DateFormat
import com.example.common.utils.formatMillisIntoDate
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.model.updates.Version
import com.example.design_system.AppTheme
import com.example.feature.update_history.R


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
                text = stringResource(id = R.string.app_update_version, model.version.toString()),
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
            UpdateDescriptionModel(Version(1, 2, 0), 1667045395445, "title", "desc", true),
        )
    }
}