package com.example.common.view_model

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * Testing approaches for multi-module navigation
 * */
abstract class Destination(val baseRoute: String) {

    abstract val arguments: List<NamedNavArgument>

    abstract val route: String

    object MainScreenDestination : Destination(baseRoute = MainScreenDestinationBaseRoute) {
        override val route: String
            get() = baseRoute

        override val arguments: List<NamedNavArgument>
            get() = listOf()
    }

    object FirstScreenDestination :
        Destination(baseRoute = FirstScreenDestinationBaseRoute) {
        override val route: String
            get() = "$baseRoute/{$UPDATE_DATA_ARG}"

        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(UPDATE_DATA_ARG) { type = NavType.BoolType }
            )

        const val UPDATE_DATA_ARG: String = "updateData"
    }

    object SecondScreenDestination
        : Destination(baseRoute = SecondScreenDestinationBaseRoute) {
        override val route: String
            get() = "$baseRoute/{$PUBLICATION_ID_ARG}/{$USER_ID_ARG}/{$FROM_FIRST_SCREEN_ARG}"

        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(PUBLICATION_ID_ARG) { type = NavType.IntType },
                navArgument(USER_ID_ARG) { type = NavType.IntType },
                navArgument(FROM_FIRST_SCREEN_ARG) { type = NavType.BoolType },
            )

        const val PUBLICATION_ID_ARG: String = "publicationId"
        const val USER_ID_ARG: String = "userId"
        const val FROM_FIRST_SCREEN_ARG: String = "fromFirstScreen"
    }

    companion object {
        private val MainScreenDestinationBaseRoute: String = "main"
        private val FirstScreenDestinationBaseRoute: String = "first"
        private val SecondScreenDestinationBaseRoute: String = "second"
    }
}

val destinationsMap = mapOf<String, Destination>()

@Composable
fun navigationSystem(
    firstFeatureEntry: FirstFeatureEntry,
    secondFeatureEntry: SecondFeatureEntry
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.MainScreenDestination.baseRoute
    ) {
        composable(Destination.MainScreenDestination.route) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "main")
            }
        }
        composable(
            Destination.FirstScreenDestination.route,
            arguments = Destination.FirstScreenDestination.arguments
        ) {
            val updateData =
                it.arguments?.getBoolean(Destination.FirstScreenDestination.UPDATE_DATA_ARG)

            firstFeatureEntry.FirstScreen(updateData = updateData ?: true)
        }
        composable(
            Destination.SecondScreenDestination.route,
            arguments = Destination.FirstScreenDestination.arguments
        ) { backStackEntry ->
            val publicationId =
                backStackEntry.arguments?.getInt(Destination.SecondScreenDestination.PUBLICATION_ID_ARG)
            val userId =
                backStackEntry.arguments?.getInt(Destination.SecondScreenDestination.USER_ID_ARG)
            val fromFirstScreen =
                backStackEntry.arguments?.getBoolean(Destination.SecondScreenDestination.FROM_FIRST_SCREEN_ARG)

            secondFeatureEntry.SecondScreen(
                publicationId = publicationId ?: 0,
                userId = userId ?: 0,
                fromFirstScreen = fromFirstScreen ?: false
            )
        }
    }
}

//get composable for screens from API interface of feature-module
interface FirstFeatureEntry {

    @Composable
    fun FirstScreen(updateData: Boolean)
}

class FirstFeatureEntryImpl : FirstFeatureEntry {

    @Composable
    override fun FirstScreen(updateData: Boolean) {
//        LaunchedEffect(key1 = viewModel.navigationEvent.collectAsState()) {
//            viewModel.navigationEvent.collect { event ->
//                event.processEvent(navigator)
//            }
//        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("1 FIRST")
            Text("updateData:$updateData")
            Button(onClick = {

            }) {
                Text("to SECOND")
            }
        }
    }

}

//get composable for screens from API interface of feature-module
interface SecondFeatureEntry {

    @Composable
    fun SecondScreen(
        publicationId: Int,
        userId: Int,
        fromFirstScreen: Boolean,
    )
}

class SecondFeatureEntryImpl : SecondFeatureEntry {

    @Composable
    override fun SecondScreen(
        publicationId: Int,
        userId: Int,
        fromFirstScreen: Boolean,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Second")
            Text("publication id: $publicationId")
            Button(onClick = {

            }) {
                Text("to First")
            }
        }
    }

}