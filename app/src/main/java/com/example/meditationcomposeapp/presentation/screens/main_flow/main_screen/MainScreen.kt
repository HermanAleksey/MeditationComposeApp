package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItem
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItemModel
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navigator: DestinationsNavigator
) {

    val menuItems = listOf(
        MenuItemModel(
            title = "Super Title",
            painterRes = R.drawable.ic_check_icon_main,
            backgroundColor = Color(169, 213, 113),
            foregroundColor = Color(106, 174, 114)
        ),
        MenuItemModel(
            title = "Mood Booster",
            painterRes = R.drawable.ic_rocket,
            backgroundColor = Color(104, 175, 156),
            foregroundColor = Color(73, 138, 120)
        ),
        MenuItemModel(
            title = "Lego",
            painterRes = R.drawable.ic_lego,
            backgroundColor = Color(62, 132, 105),
            foregroundColor = Color(43, 91, 84)
        ),
        MenuItemModel(
            title = "Chill Relax",
            painterRes = R.drawable.ic_audacity,
            backgroundColor = Color(106, 174, 114),
            foregroundColor = Color(62, 132, 105)
        ),
        MenuItemModel(
            title = "Super Finger",
            painterRes = R.drawable.ic_touch_id,
            backgroundColor = Color(154, 154, 154),
            foregroundColor = Color(177, 177, 177)
        )
    )

    ColorBackground(color = com.example.meditationcomposeapp.ui.theme.ColorBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            Toolbar()
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.features),
                    fontSize = 34.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = Alegreya,
                    fontWeight = FontWeight.W500,
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