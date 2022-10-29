package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItem
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItemModel
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.UpdateDescriptionDialog
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.getMenuItemsList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        setBottomNavBarVisible = {},
        viewModel = hiltViewModel(),
        navigator = EmptyDestinationsNavigator
    )
}

@Destination
@Composable
fun MainScreen(
    setBottomNavBarVisible: (Boolean) -> Unit,
    viewModel: MainScreenViewModel,
    navigator: DestinationsNavigator,
) {
    val uiState = viewModel.iuState
    setBottomNavBarVisible(true)
    val activity = LocalContext.current as? Activity
    BackHandler(enabled = true, onBack = {
        activity?.finish()
    })

    val menuItems = getMenuItemsList(navigator)

    ColorBackground(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            Toolbar()
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.features),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_top_screen_title),
                        start = dimensionResource(id = R.dimen.padding_horizontal_main_content)
                    )
                )
                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_spacing_menu_item)))
                MainMenu(menuItems = menuItems)
            }
        }

        if (uiState.updateNotesDialogVisible)
            UpdateDescriptionDialog(updatesLog = uiState.updateNotesList, onBackgroundClick = {
                viewModel.onDialogBackgroundClick()
            })
    }
}

@Composable
fun MainMenu(menuItems: List<MenuItemModel>) {
    for (item in menuItems.indices step 2) {
        val rowHasTwoItems = item + 1 < menuItems.size
        val spacingMenuItem = dimensionResource(R.dimen.padding_spacing_menu_item)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.height_menu_item))
                .padding(horizontal = dimensionResource(R.dimen.padding_horizontal_main_content)),
            horizontalArrangement = if (rowHasTwoItems)
                Arrangement.Center else Arrangement.Start
        ) {
            MenuItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                menuItems[item]
            )

            Spacer(modifier = Modifier.width(spacingMenuItem))

            if (rowHasTwoItems) {
                MenuItem(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    menuItems[item + 1]
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(spacingMenuItem))
    }
}