package com.justparokq.feature.chat.internal.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun ChatTextInput(
    text: String,
    onTextChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier
                .height(60.dp)
        )

        Button(onClick = { onSendClicked() }) {
            Text(text = "Send")
        }
    }
}

@Preview
@Composable
fun ChatTextInputPreview() {
    MaterialTheme {
        ChatTextInput(
            text = "Inputted Text",
            onSendClicked = {},
            onTextChanged = {}
        )
    }
}