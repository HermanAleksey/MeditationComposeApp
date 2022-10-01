package com.example.shuffle_puzzle.presentation.puzzle_board

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue
import kotlin.reflect.KFunction1

@Composable
fun EmptyStatePuzzleBoard(puzzleTemplates: List<Int>, onTemplateSelected: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        var currentPage = remember { 0 }
        fun onCurrentPageChanged(index: Int) {
            currentPage = index
        }

        ImagePager(puzzleTemplates, ::onCurrentPageChanged)

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
            Button(
                onClick = { onTemplateSelected(currentPage) },
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp),
            ) {
                Text(text = "Select")
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager(puzzleTemplates: List<Int>, onCurrentPageChanged: KFunction1<Int, Unit>) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onCurrentPageChanged(page)
        }
    }

    HorizontalPager(
        count = puzzleTemplates.size,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentPadding = PaddingValues(horizontal = 64.dp),
        state = pagerState,
    ) { pageNumber ->
        Card(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = calculateCurrentOffsetForPage(pageNumber).absoluteValue

                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f.dp,
                    stop = 1f.dp,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale.value
                    scaleY = scale.value
                }

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f.dp,
                    stop = 1f.dp,
                    fraction = (1f - pageOffset.coerceIn(0f, 1f))
                ).value
            }
            .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(puzzleTemplates[pageNumber]),
                contentDescription = null
            )
        }
    }
}