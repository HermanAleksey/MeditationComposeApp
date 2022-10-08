import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R
import com.example.shuffle_puzzle.presentation.puzzle_description.states.InGameFunctionsDescription
import com.example.shuffle_puzzle.presentation.puzzle_description.states.PuzzleSizeSelection

@Composable
fun PuzzleGameDescriptionCard(
    isPuzzleCreated: Boolean,
    puzzleImageBitmap: Bitmap?,
    movesDone: Int,
    onRestartPuzzle: () -> Unit,
    timerValueSec: Long,
    onTimerSecTick: () -> Unit,
    isTimerActivated: Boolean,
    puzzleSize: Int,
    updateSelectedSizeValue: (Int) -> Unit,
    modifier: Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
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
                modifier = Modifier.fillMaxSize(),
            ) {
                if (isPuzzleCreated)
                    InGameFunctionsDescription(
                        timerValueSec = timerValueSec,
                        onTimerSecTick = onTimerSecTick,
                        isTimerActivated = isTimerActivated,
                        movesDone = movesDone,
                        onRestartPuzzle = onRestartPuzzle
                    )
                else
                    PuzzleSizeSelection(
                        puzzleSize = puzzleSize,
                        updateSelectedSizeValue = updateSelectedSizeValue
                    )
            }
        }
    }
}