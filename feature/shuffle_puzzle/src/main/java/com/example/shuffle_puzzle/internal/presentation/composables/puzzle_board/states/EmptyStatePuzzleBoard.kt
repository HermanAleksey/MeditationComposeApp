package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.feature.shuffle_puzzle.R
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.SELECT_YOUR_IMAGE
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.SelectPuzzleImageFromGalleryCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@Composable
internal fun SelectPuzzleBoard(
    onCreatePuzzleClick: (Bitmap) -> Unit,
) {
    val resources = LocalContext.current.resources
    val templatePainters = listOf(
        R.drawable.shuffle_puzzle_template_1,
        R.drawable.shuffle_puzzle_template_2,
        R.drawable.shuffle_puzzle_template_3,
        SELECT_YOUR_IMAGE
    )

    val (currentPage, onCurrentPageChanged) = remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxWidth()) {

        PuzzleImagePager(
            puzzleTemplates = templatePainters,
            onCurrentPageChanged = onCurrentPageChanged,
            onPuzzleImageSelected = { selectedUserBitmap ->
                onCreatePuzzleClick(selectedUserBitmap)
            }
        )

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
            Button(
                enabled = currentPage != templatePainters.lastIndex,
                onClick = {
                    val bitmap = BitmapFactory.decodeResource(
                        resources,
                        templatePainters[currentPage]
                    )

                    onCreatePuzzleClick(bitmap)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(horizontal = 42.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary, contentColor = Color.White, ),
            ) {
                Text(
                    text = stringResource(id = R.string.select_puzzle),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun PuzzleImagePager(
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
                    contentDescription = null
                )
        }
    }
}