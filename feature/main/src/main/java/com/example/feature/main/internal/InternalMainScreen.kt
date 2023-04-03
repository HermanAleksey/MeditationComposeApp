package com.example.feature.main.internal

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.design_system.common_composables.ColorBackground
import com.example.feature.main.R
import com.example.feature.main.internal.composable.MenuItem
import com.example.feature.main.internal.composable.MenuItemModel
import com.example.feature.main.internal.composable.getMenuItemsList

@Composable
internal fun InternalMainScreen(
    onMenuItemClick: (MenuItem) -> Unit,
) {
    val activity = LocalContext.current as? Activity
    BackHandler(enabled = true, onBack = {
        activity?.finish()
    })

    val menuItems = getMenuItemsList {
        onMenuItemClick(it)
    }

    ColorBackground(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(46.dp))
            Text(
                text = stringResource(id = R.string.features),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_horizontal_main_content)
                )
            )
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_spacing_menu_item)))
            MainMenu(menuItems = menuItems)
        }
    }
}

@Composable
internal fun MainMenu(
    menuItems: List<MenuItemModel>,
) {
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

@Preview
@Composable
fun InternalMainScreenPreview() {
    AppTheme {
        InternalMainScreen {}
    }
}