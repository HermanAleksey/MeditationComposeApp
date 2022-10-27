package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
import com.example.meditationcomposeapp.presentation.screens.destinations.ShufflePuzzleScreenDestination
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItem
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItemModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        setBottomNavBarVisible = {},
        viewModel = MainScreenViewModel(),
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
    setBottomNavBarVisible(true)
    val activity = LocalContext.current as? Activity
    BackHandler(enabled = true, onBack = {
        activity?.finish()
    })

    val menuItems = listOf(
        MenuItemModel(
            title = "Shuffle Puzzle",
            painterRes = R.drawable.ic_check_icon_main,
            backgroundColor = Color(169, 213, 113),
            foregroundColor = Color(106, 174, 114),
            onClick = { navigator.navigate(ShufflePuzzleScreenDestination()) },
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