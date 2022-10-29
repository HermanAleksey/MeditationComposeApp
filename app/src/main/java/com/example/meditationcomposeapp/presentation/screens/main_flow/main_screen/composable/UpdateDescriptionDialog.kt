package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.updates_log.UpdateDescriptionModel
import com.example.meditationcomposeapp.presentation.utils.DateFormat
import com.example.meditationcomposeapp.presentation.utils.formatMillisIntoDate
import com.example.meditationcomposeapp.ui.theme.MeditationComposeAppTheme

@Preview
@Composable
fun UpdateDescriptionDialogPreview() {
    MeditationComposeAppTheme(false) {
        UpdateDescriptionDialog(listOf(
            UpdateDescriptionModel("10.10.10", 1667045395445, "title", "desc", true),
            UpdateDescriptionModel("10.10.10", 1667045395445, "title", "desc", true)
        ))
    }
}

@Composable
fun UpdateDescriptionDialog(
    updatesLog: List<UpdateDescriptionModel>,
    onBackgroundClick: () -> Unit = {},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = 0.3f))
        .clickable { onBackgroundClick() },
        contentAlignment = Alignment.Center
    ) {
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp


        Card(
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .fillMaxWidth()
                .heightIn(max = screenHeight.value.times(0.8).dp )
                .clickable { },
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_pop_up_corner))
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.update_notes_dialog_title),
                        style = MaterialTheme.typography.h2.copy(
                            color = MaterialTheme.colors.onSurface
                        ),
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
                    )
                }
                items(updatesLog) {
                    UpdateDescriptionElement(it)
                }
                items(updatesLog) {
                    UpdateDescriptionElement(it)
                }
                items(updatesLog) {
                    UpdateDescriptionElement(it)
                }
                items(updatesLog) {
                    UpdateDescriptionElement(it)
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun UpdateDescriptionElement(model: UpdateDescriptionModel) {
    val locale = LocalContext.current.resources.configuration.locales[0]
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(id = R.string.app_update_version, model.versionName),
                style = MaterialTheme.typography.caption.copy(
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
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = model.updateTitle, style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onSurface
        ))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = model.updateDescription,
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
    }
}