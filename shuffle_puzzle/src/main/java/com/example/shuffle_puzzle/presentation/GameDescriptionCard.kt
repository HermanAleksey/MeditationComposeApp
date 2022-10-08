import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R

@Composable
fun PuzzleGameDescriptionCard(
    isPuzzleCreated: Boolean,
    puzzleImageBitmap: Bitmap?,
    movesDone: Int,
    refreshPuzzle: () -> Unit,
    restartPuzzle: () -> Unit,
    modifier: Modifier,
    puzzleSize: Int,
    updateSelectedSizeValue: (Int) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            if (puzzleImageBitmap == null) {
                Image(
                    painter = painterResource(id = R.drawable.empty_puzzle_image),
                    contentDescription = stringResource(id = R.string.completed_puzzle),
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.FillHeight,
                )
            } else {
                Image(
                    bitmap = puzzleImageBitmap.asImageBitmap(),
                    contentDescription = stringResource(id = R.string.completed_puzzle),
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.FillHeight,
                )
            }

            Column(
                modifier = Modifier
                    .weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isPuzzleCreated)
                    InGameFunctionsDescription(movesDone, refreshPuzzle, restartPuzzle)
                else
                    PuzzleSizeSelection(puzzleSize, updateSelectedSizeValue)
            }
        }
    }
}

@Composable
fun InGameFunctionsDescription(
    movesDone: Int,
    refreshPuzzle: () -> Unit,
    restartPuzzle: () -> Unit,
) {
    Text(text = stringResource(id = R.string.moves_done))
    Text(text = movesDone.toString())
    Row {
        Button(onClick = {
            refreshPuzzle()
        }) {
            Text(text = "Refresh")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = {
            restartPuzzle()
        }) {
            Text(text = "Restart")
        }
    }
}


@Composable
fun PuzzleSizeSelection(puzzleSize: Int, updateSelectedSizeValue: (Int) -> Unit) {
    val sizeOptions = integerArrayResource(id = R.array.puzzle_sizes)
    val (_, onOptionSelected) = remember { mutableStateOf(sizeOptions[0]) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.select_puzzle_size))

        Row(modifier = Modifier.selectableGroup()) {
            sizeOptions.forEach { size ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RadioButton(
                        selected = (size == puzzleSize),
                        onClick = {
                            onOptionSelected(size)
                            updateSelectedSizeValue(size)
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(text = "${size}x$size")
                }

                if (size != sizeOptions.last()) {
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
    }
}