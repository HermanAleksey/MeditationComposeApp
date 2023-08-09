import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.core.design_system.AppTheme
import com.justparokq.feature.shuffle_puzzle.R
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleAction
import com.justparokq.feature.shuffle_puzzle.api.ShufflePuzzleState
import com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.puzzle_description.states.InGameFunctionsDescription
import com.justparokq.feature.shuffle_puzzle.internal.presentation.composables.puzzle_description.states.PuzzleSizeSelection
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun PuzzleGameDescriptionCard(
    modifier: Modifier,
    processAction: (ShufflePuzzleAction) -> Unit,
    uiState: State<ShufflePuzzleState>,
) {
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
                        onRestartPuzzle = { processAction(ShufflePuzzleAction.OnRestartClicked) }
                    )
                else
                    PuzzleSizeSelection(
                        puzzleSize = uiState.value.puzzleSize,
                        updateSelectedSizeValue = { size ->
                            processAction(ShufflePuzzleAction.OnPuzzleSizeChanged(size))
                        }
                    )
            }
        }
    }
}

@Preview
@Composable
internal fun PuzzleBoardWithCounterPreview() {
    AppTheme {
        PuzzleGameDescriptionCard(
            modifier = Modifier
                .padding(start = 18.dp, end = 18.dp, top = 18.dp)
                .fillMaxWidth()
                .height(150.dp),
            processAction = { },
            uiState = MutableStateFlow(ShufflePuzzleState()).collectAsState(),
        )
    }
}