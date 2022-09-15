package com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItem
import com.example.meditationcomposeapp.presentation.screens.main_flow.main_screen.composable.MenuItemModel
import com.example.meditationcomposeapp.ui.theme.Alegreya

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
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
                    text = "Tools",
                    fontSize = TextUnit(35F, TextUnitType.Sp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = Alegreya,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.padding(top = 50.dp, start = 24.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                MainMenu(menuItems = menuItems)
            }
        }
    }
}

@Composable
fun MainMenu(menuItems: List<MenuItemModel>) {
    for (item in menuItems.indices step 2) {
        val rowHasTwoItems = item + 1 < menuItems.size

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            MenuItem(
                menuItems[item]
            )
            if (rowHasTwoItems) {
                Spacer(modifier = Modifier.width(20.dp))
                MenuItem(
                    menuItems[item + 1]
                )
            } else {
                //todo replace with some resource
                Spacer(modifier = Modifier.width(20.dp + 153.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}