package com.example.design_system.common_composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.SilverSand

@Composable
fun LoadingElement() {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
        modifier = Modifier
            .size(90.dp)
    ) {
        CircularProgressIndicator(
            color = SilverSand,
            strokeWidth = 7.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}