package com.example.meditationcomposeapp.presentation.screens.main_flow.bottom_nav_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.meditationcomposeapp.presentation.screens.NavGraphs
import com.example.meditationcomposeapp.presentation.screens.appCurrentDestinationAsState
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.TypedDestination
import com.example.meditationcomposeapp.presentation.screens.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun BottomBar(navController: NavController) {
    val currentDestination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        BottomBarDestinations.values().forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarDestinations,
    currentDestination: TypedDestination<*>?,
    navController: NavController,
) {
    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.caption
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "${screen.title} navigation icon"
            )
        },
        selected = currentDestination == screen.direction,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            with(navController) {
                navigate(screen.direction) {
                    popUpTo(MainScreenDestination.route) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            }
        }
    )
}