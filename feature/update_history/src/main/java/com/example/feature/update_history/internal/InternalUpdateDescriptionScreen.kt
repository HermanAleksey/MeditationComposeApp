package com.example.feature.update_history.internal

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
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.design_system.AppTheme
import com.example.feature.update_history.R

@Composable
internal fun InternalUpdatesDescriptionScreen(
    updatesList: List<UpdateDescriptionModel>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
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
        itemsIndexed(updatesList) { index, item ->
            UpdateDescriptionElement(item)

            if (index < updatesList.lastIndex) {
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
private fun UpdatesDescriptionScreenPreview() {
    AppTheme {
        InternalUpdatesDescriptionScreen(
            listOf(
                UpdateDescriptionModel(
                    "0.0.1",
                    1667045335445,
                    "First release!",
                    "First attempt of doing smt. Added nothing for user!!! ",
                    true
                ),
                UpdateDescriptionModel(
                    "1.0.0",
                    1667045395445,
                    "Update design system",
                    "Update fonts, colors and many other things! Hurry up and try out all new stuff by yourself!",
                    true
                )
            )
        )
    }
}