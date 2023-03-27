package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.states

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.AppTheme
import com.example.feature.shuffle_puzzle.R
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board.SELECT_YOUR_IMAGE

@Composable
internal fun EmptyStatePuzzleBoard(
    onCreatePuzzleClick: (Bitmap) -> Unit,
) {
    val resources = LocalContext.current.resources
    val templatePainters = remember {
        listOf(
            R.drawable.shuffle_puzzle_template_1,
            R.drawable.shuffle_puzzle_template_2,
            R.drawable.shuffle_puzzle_template_3,
            SELECT_YOUR_IMAGE
        )
    }

    val (currentPage, onCurrentPageChanged) = remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {

        PuzzleImageSelectPager(
            puzzleTemplates = templatePainters,
            onCurrentPageChanged = onCurrentPageChanged,
            onPuzzleImageSelected = { selectedUserBitmap ->
                onCreatePuzzleClick(selectedUserBitmap)
            }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                enabled = currentPage != templatePainters.lastIndex,
                onClick = {
                    val bitmap = BitmapFactory.decodeResource(
                        resources, templatePainters[currentPage]
                    )
                    onCreatePuzzleClick(bitmap)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.select_puzzle),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
internal fun SelectPuzzleBoardPreview() {
    AppTheme {
        EmptyStatePuzzleBoard {}
    }
}