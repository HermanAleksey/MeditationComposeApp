package com.example.feature.update_history.internal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.core.model.updates.UpdateDescriptionModel
import com.example.core.model.updates.Version
import com.example.design_system.AppTheme
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.update_history.api.UpdatesDescriptionViewState
import com.example.feature.update_history.internal.composables.RateUsCard
import com.example.feature.update_history.internal.composables.UpdateDescriptionElement

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun InternalUpdatesDescriptionScreen(
    uiState: UpdatesDescriptionViewState,
    onCancelRateUsClick: () -> Unit,
) {
    ColorBackground(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item(key = 0) {
                    AnimatedVisibility(
                        visible = uiState.isRateUsCardVisible,
                        exit = slideOut(targetOffset = { IntOffset(it.width, 0) })
                    ) {
                        RateUsCard(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .animateItemPlacement(),
                            onCancelClick = {
                                onCancelRateUsClick()
                            },
                            onRateUsClick = {
                                /**todo need to add rate logic. Pop-up with rating?
                                also track, if user already rated app, so
                                this notifications is not shown to those, who did**/
                            }
                        )
                    }
                }

                itemsIndexed(
                    items = uiState.updatesList,
                    key = { index: Int, _ -> index + 1 }
                ) { index, item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .animateItemPlacement(),
                        backgroundColor = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(6.dp))
                            UpdateDescriptionElement(item)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun UpdatesDescriptionScreenPreview() {
    AppTheme {
        InternalUpdatesDescriptionScreen(
            UpdatesDescriptionViewState(
                updatesList = listOf(
                    UpdateDescriptionModel(
                        Version(0, 0, 1),
                        1667045335445,
                        "First release!",
                        "First attempt of doing smt. Added nothing for user!!! ",
                        true
                    ),
                    UpdateDescriptionModel(
                        Version(0,0,2),
                        1667045395445,
                        "Update design system",
                        "Update fonts, colors and many other things! Hurry up and try out all new stuff by yourself!",
                        true
                    ),
                    UpdateDescriptionModel(
                        Version(0,1,3),
                        1680264843000,
                        "Update design system",
                        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
                        true
                    )
                )
            ),
            onCancelRateUsClick = {}
        )
    }
}