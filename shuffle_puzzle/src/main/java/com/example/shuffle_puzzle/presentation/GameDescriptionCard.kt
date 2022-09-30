import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shuffle_puzzle.R

@Composable
fun PuzzleGameDescriptionCard(movesDone: Int, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = stringResource(id = R.string.completed_puzzle),
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight,
            )
            Column(
                modifier = Modifier
                    .weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.moves_done))
                Text(text = movesDone.toString())
                Row {
                    Button(onClick = { }) {
                        Text(text = "Gallery")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = { }) {
                        Text(text = "Refresh")
                    }
                }
            }
        }
    }
}
