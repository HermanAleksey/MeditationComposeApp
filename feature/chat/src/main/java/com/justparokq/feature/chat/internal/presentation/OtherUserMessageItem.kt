package com.justparokq.feature.chat.internal.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justparokq.feature.chat.internal.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.model.MessageUiModel
import com.justparokq.feature.chat.internal.model.OtherUserMessageUIModel

@Composable
internal fun MessageItem(message: MessageUiModel) {
    when (message) {
        is OtherUserMessageUIModel -> OtherUserMessageItem(message)
        is CurrentUserMessageUIModel -> CurrentUserMessageItem(message)
    }
}

@Composable
private fun OtherUserMessageItem(message: OtherUserMessageUIModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .size(46.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message.userName,
                style = MaterialTheme.typography.h4
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Card(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 72.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = message.time,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}

@Composable
private fun CurrentUserMessageItem(message: CurrentUserMessageUIModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 72.dp, end = 24.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {

        //todo replace with image
        Text(text = if (message.isSent) "S" else "L", color = Color.Blue)

        Card(
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = message.time,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}

@Preview(name = "Message from other users")
@Composable
fun MessageItemPreview() {
    MaterialTheme() {
        MessageItem(
            OtherUserMessageUIModel(
                text = "Message text",
                time = "15:30",
                userName = "K",
            )
        )
    }
}


@Preview(name = "Message from other users long text")
@Composable
fun MessageItemPreviewLongText() {
    MaterialTheme() {
        MessageItem(
            OtherUserMessageUIModel(
                text = "Message text is very big. task (current target is 11) and 'kaptGenerateStubsDebugKotlin' task (current target is 1.8) jvm target compatibility should be set to the same Java version.",
                time = "15:30",
                userName = "K",
            )
        )
    }
}

@Preview(name = "Message from current user")
@Composable
fun MessageItemPreviewCurrentUser() {
    MaterialTheme() {
        MessageItem(
            CurrentUserMessageUIModel(
                text = "Message text",
                time = "15:30",
                isSent = false
            )
        )
    }
}


@Preview(name = "Sent message from current user")
@Composable
fun MessageItemPreviewCurrentUserSent() {
    MaterialTheme() {
        MessageItem(
            CurrentUserMessageUIModel(
                text = "Message text",
                time = "15:30",
                isSent = true
            )
        )
    }
}


@Preview(name = "Message from current user long text")
@Composable
fun MessageItemPreviewCurrentUserLongText() {
    MaterialTheme {
        MessageItem(
            CurrentUserMessageUIModel(
                text = "Message text is very big. task (current target is 11) and 'kaptGenerateStubsDebugKotlin' task (current target is 1.8) jvm target compatibility should be set to the same Java version.",
                time = "15:30",
                isSent = false
            )
        )
    }
}