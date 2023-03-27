import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.feature.shuffle_puzzle.R
import com.example.shuffle_puzzle.api.ShufflePuzzleScreenViewModel
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_description.states.InGameFunctionsDescription
import com.example.shuffle_puzzle.internal.presentation.composables.puzzle_description.states.PuzzleSizeSelection

@Composable
internal fun PuzzleGameDescriptionCard(
    modifier: Modifier,
    viewModel: ShufflePuzzleScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    Card(
        modifier = modifier
            .background(MaterialTheme.colors.onSurface)
            .clip(RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            uiState.value.puzzle.let { puzzle ->
                if (puzzle == null) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_puzzle_image),
                        contentDescription = stringResource(id = R.string.completed_puzzle),
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillHeight,
                    )
                } else {
                    Image(
                        bitmap = puzzle.imageBitmap.asImageBitmap(),
                        contentDescription = stringResource(id = R.string.completed_puzzle),
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillHeight,
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                if (uiState.value.puzzle != null)
                    InGameFunctionsDescription(
                        timerValueSec = uiState.value.solvingTimerSec,
                        movesDone = uiState.value.movesDone,
                        onRestartPuzzle = { viewModel.onRestartPuzzleClicked() }
                    )
                else
                    PuzzleSizeSelection(
                        puzzleSize = uiState.value.puzzleSize,
                        updateSelectedSizeValue = { viewModel.onPuzzleSizeChanged(it) }
                    )
            }
        }
    }
}