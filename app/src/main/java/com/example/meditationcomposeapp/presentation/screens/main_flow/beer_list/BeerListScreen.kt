package com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.meditationcomposeapp.model.entity.Beer
import com.example.meditationcomposeapp.presentation.common_composables.ColorBackground
import com.example.meditationcomposeapp.presentation.common_composables.Toolbar
import com.example.meditationcomposeapp.presentation.screens.main_flow.beer_list.composable.BeerItem
import com.example.meditationcomposeapp.ui.theme.colorBackground

@Composable
fun BeerListScreen(
    viewModel: BeerListViewModel
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
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { data ->
            BeerItem(beer = data)
        }
    }
}