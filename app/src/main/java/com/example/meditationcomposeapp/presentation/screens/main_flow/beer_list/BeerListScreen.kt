package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable.BeerItem
import com.example.meditationcomposeapp.ui.theme.colorBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BeerListScreen(
    viewModel: BeerListScreenViewModel,
    navigator: DestinationsNavigator
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.onFirstLaunch()
    })

    ColorBackground(
        isLoading = viewModel.isLoading(),
        lockScreenWhenLoading = true,
        color = MaterialTheme.colors.colorBackground
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Toolbar()
            BeerList(viewModel.getBeerList())
        }
    }
}

@Composable
fun BeerList(list: List<Beer>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal_list))
            .background(MaterialTheme.colors.colorBackground),
    ) {
        items(list) { data ->
            BeerItem(beer = data)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}