package com.example.meditationcomposeapp.presentation.screens.main_flow.shuffle_puzzle.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.meditationcomposeapp.R
import com.example.meditationcomposeapp.ui.theme.Alegreya
import com.example.meditationcomposeapp.ui.theme.colorPopUpBackground

@OptIn(ExperimentalUnitApi::class)
@Composable
fun PuzzleIsSolvedLabel() {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_pop_up_corner)),
        backgroundColor = MaterialTheme.colors.colorPopUpBackground
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.puzzle_solved),
                fontSize = TextUnit(25F, TextUnitType.Sp),
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = Alegreya,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(horizontal = 60.dp, vertical = 25.dp)
            )
        }
    }
}