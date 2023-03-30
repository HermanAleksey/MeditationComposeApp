package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.design_system.AppTheme
import com.example.feature.shuffle_puzzle.R
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.SELECT_YOUR_IMAGE
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.SelectPuzzleImageFromGalleryCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun PuzzleImageSelectPager(
    puzzleTemplates: List<Int>,
    onCurrentPageChanged: (Int) -> Unit,
    onPuzzleImageSelected: (Bitmap) -> Unit,
) {
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
        contentPadding = PaddingValues(horizontal = 48.dp),
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
            if (puzzleTemplates[pageNumber] == SELECT_YOUR_IMAGE) {
                Box(contentAlignment = Alignment.Center) {
                    SelectPuzzleImageFromGalleryCard(
                        modifier = Modifier
                            .background(Color(0xFFB8B8B8))
                            .fillMaxSize(),
                        onPuzzleImageSelected
                    )
                }
            } else
                Image(
                    painter = painterResource(puzzleTemplates[pageNumber]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
        }
    }
}

@Preview
@Composable
internal fun PuzzleImageSelectPagerPreview() {
    AppTheme {
        PuzzleImageSelectPager(
            puzzleTemplates = listOf(
                R.drawable.shuffle_puzzle_template_1,
                R.drawable.shuffle_puzzle_template_2,
                R.drawable.shuffle_puzzle_template_3,
                SELECT_YOUR_IMAGE
            ),
            onCurrentPageChanged = {},
            onPuzzleImageSelected = {}
        )
    }
}