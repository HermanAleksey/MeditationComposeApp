package com.example.feature.main.internal

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.navigation.NavDependenciesProvider
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.main.R
import com.example.feature.main.api.MainScreenNavDependencies
import com.example.feature.main.api.MainScreenViewModel
import com.example.feature.main.internal.composable.MenuItem
import com.example.feature.main.internal.composable.MenuItemModel
import com.example.feature.main.internal.composable.getMenuItemsList

@Preview
@Composable
fun MainScreenPreview() {
    InternalMainScreen(
        viewModel = hiltViewModel(),
    )
}

@Composable
internal fun InternalMainScreen(
    viewModel: MainScreenViewModel,
) {
    val activity = LocalContext.current as? Activity
    BackHandler(enabled = true, onBack = {
        activity?.finish()
    })

    val navDependencies = ((LocalContext.current as? Activity) as NavDependenciesProvider)
        .provideDependencies(MainScreenNavDependencies::class.java)

    LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
        viewModel.navigationEvent.collect { event ->
            event?.tryNavigate(navDependencies)
        }
    }

    val menuItems = getMenuItemsList {
        viewModel.onMenuItemClick(it)
    }

    ColorBackground(color = MaterialTheme.colors.background) {
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
}

@Composable
internal fun MainMenu(menuItems: List<MenuItemModel>) {
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