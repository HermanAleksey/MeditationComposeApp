package com.example.meditationcomposeapp.presentation.ui_controls.bottom_nav_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.utils.UiText
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.presentation.screens.NavGraphs
import com.example.meditationcomposeapp.presentation.screens.appCurrentDestinationAsState
import com.example.meditationcomposeapp.presentation.screens.destinations.BeerListScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.MainScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.ProfileScreenDestination
import com.example.meditationcomposeapp.presentation.screens.destinations.TypedDestination
import com.example.meditationcomposeapp.presentation.screens.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestinations(
    val direction: DirectionDestinationSpec,
    val description: UiText,
    val icon: ImageVector,
) {
    Main(
        direction = MainScreenDestination,
        description = UiText.StringResource(R.string.home_screen_desc),
        icon = Icons.Default.Home
    ),

    BeerList(
        direction = BeerListScreenDestination,
        description = UiText.StringResource(R.string.beers_screen_desc),
        icon = Icons.Default.Coffee
    ),

    ProfileScreen(
        direction = ProfileScreenDestination,
        description = UiText.StringResource(R.string.profile_screen_desc),
        icon = Icons.Default.Logout
    );
}

@Composable
fun NavigationBottomBar(navController: NavController) {
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
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.description.asString(),
                modifier = Modifier.size(26.dp)
            )
        },
        selected = currentDestination == screen.direction,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            if (navController.currentDestination?.route == screen.direction.route)
                return@BottomNavigationItem

            with(navController) {
                navigate(screen.direction) {
                    popUpTo(MainScreenDestination.route) {
                        saveState = true
                        inclusive = false
                    }
                }
            }
        }
    )
}
