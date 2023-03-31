package com.example.meditationcomposeapp.presentation.ui_controls.toolbar

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.navigation.NavDependenciesProvider
import com.example.design_system.dialog.DialogController
import com.example.design_system.toolbar.ToolbarProvider
import com.example.feature.update_history.api.UpdateDescriptionDialogProvider
import com.example.meditationcomposeapp.R

class MedioseToolbarProvider(
    private val viewModel: ToolbarViewModel,
    private val dialogController: DialogController,
) : ToolbarProvider() {

    @Composable
    override fun Display() {
        val navDependencies = ((LocalContext.current as? Activity) as? NavDependenciesProvider)
            ?.provideDependencies(ToolbarNavDependencies::class.java)

        LaunchedEffect(key1 = Unit) {
            viewModel.onScreenEntered()
        }

        LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState(null)) {
            viewModel.navigationEvent.collect { event ->
                event?.let { navRoute ->
                    navDependencies?.let {
                        navRoute.tryNavigate(it)
                    }
                }
            }
        }


        val uiState = viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.onLaunch()
        }

        LaunchedEffect(key1 = uiState.value.lastUpdate) {
            uiState.value.lastUpdate?.let { lastUpdate ->
                if (!lastUpdate.wasShown) {
                    dialogController.show(
                        UpdateDescriptionDialogProvider(
                            listOf(lastUpdate)
                        )
                    )
                }
            }
        }

        LaunchedEffect(
            key1 = uiState.value.updateNotesList,
            key2 = uiState.value.isDialogShown
        ) {
            if (uiState.value.updateNotesList.isNotEmpty() && uiState.value.isDialogShown)
                dialogController.show(
                    UpdateDescriptionDialogProvider(
                        uiState.value.updateNotesList,
                        onDismissSideEffect = {
                            viewModel.onDialogClosed()
                        }
                    )
                )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_toolbar))
                .background(color = MaterialTheme.colors.background)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_hamburger),
                contentDescription = "ic_hamburger image",
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = com.example.core.design_system.R.drawable.ic_app_icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = Icons.Rounded.Newspaper,
                contentDescription = "Updates news",
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        viewModel.onUpdateHistoryClick()
                    },
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
            )
        }
    }
}