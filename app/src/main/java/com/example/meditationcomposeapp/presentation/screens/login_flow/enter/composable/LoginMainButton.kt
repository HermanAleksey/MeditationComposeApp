package com.example.meditationcomposeapp.presentation.screens.login_flow.enter.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginMainButton(
    text: String,
    modifier: Modifier,
    onClick: ()->Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }
}