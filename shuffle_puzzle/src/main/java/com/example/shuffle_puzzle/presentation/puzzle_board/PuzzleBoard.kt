package com.example.shuffle_puzzle.presentation.puzzle_board

import android.graphics.BitmapFactory
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R
import com.example.shuffle_puzzle.model.Puzzle

@Composable
fun PuzzleBoard(onMovePerformed: (Boolean) -> Unit, modifier: Modifier) {
    var puzzle: Puzzle? by remember {
        mutableStateOf(null)
    }


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        if (puzzle == null) {
            val templatePainters = listOf(
                R.drawable.shuffle_puzzle_template_1,
                R.drawable.shuffle_puzzle_template_2,
                R.drawable.shuffle_puzzle_template_3,
            )


            val resources = LocalContext.current.resources
            fun onTemplateSelected(index: Int) {
                val bitmap = BitmapFactory.decodeResource(
                    resources,
                    templatePainters[index]
                )

                puzzle = Puzzle(3, 3, bitmap)
            }

            EmptyStatePuzzleBoard(templatePainters, ::onTemplateSelected)
        } else
            FilledStatePuzzleBoard(puzzle!!, onMovePerformed, modifier)
    }
}